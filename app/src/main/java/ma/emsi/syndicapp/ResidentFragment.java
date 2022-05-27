package ma.emsi.syndicapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ma.emsi.syndicapp.adapter.ResidentAdapter;
import ma.emsi.syndicapp.bean.Resident;
import ma.emsi.syndicapp.databinding.FragmentGalleryBinding;
import ma.emsi.syndicapp.ui.gallery.GalleryViewModel;


public class ResidentFragment extends Fragment {

    private static int id;
    private static int position;
    private ListView list;
    static private View v ;
    private FragmentGalleryBinding binding;
    static private List<Resident> residents;
    RequestQueue requestQueue;
    String getUrl = "http://192.168.56.1:8000/api/residents";

    public static List<Resident> getResidents() {
        return residents;
    }

    public static void setResidents(List<Resident> residents) {
        ResidentFragment.residents = residents;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = root.findViewById(R.id.listView);
        setData();

        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
    private void setData() {
        requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.GET,
                getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("data", response);
                Type type = new TypeToken<Collection<Resident>>() {
                }.getType();
                List<Resident>data = new Gson().fromJson(response, type);
                Log.d("data", data.toString());
                ResidentFragment.setResidents(data);
                update_liste();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error : " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return null;
            }
        };
        requestQueue.add(request);

    }

    private void update_liste() {
        this.list.setAdapter(new ResidentAdapter(getActivity(), residents));
        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResidentFragment.position = position;

                Intent i = new Intent(getActivity(), UpdateResidentActivity.class);
                i.putExtra("id",residents.get(position).getId()+"");
                i.putExtra("lmmeuble",residents.get(position).getLmmeuble()+"");
                i.putExtra("appartement",residents.get(position).getAppartement()+"");
                i.putExtra("nom",residents.get(position).getNom()+"");
                i.putExtra("prenom",residents.get(position).getPrenom()+"");
                i.putExtra("ville",residents.get(position).getVille()+"");
                i.putExtra("email",residents.get(position).getEmail()+"");
                i.putExtra("tele",residents.get(position).getTele()+"");

                startActivity(i);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}