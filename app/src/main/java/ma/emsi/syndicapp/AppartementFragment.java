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

import ma.emsi.syndicapp.adapter.AppartementAdapter;
import ma.emsi.syndicapp.bean.Appartement;
import ma.emsi.syndicapp.databinding.FragmentGalleryBinding;
import ma.emsi.syndicapp.ui.gallery.GalleryViewModel;


public class AppartementFragment extends Fragment {

    private static int id;
    private static int position;
    private ListView list;
    static private View v ;
    private FragmentGalleryBinding binding;
    static private List<Appartement> appartements;
    RequestQueue requestQueue;
    String getUrl = "http://192.168.56.1:8000/api/appartements";

    public static List<Appartement> getAppartements() {
        return appartements;
    }

    public static void setAppartements(List<Appartement> appartements) {
        AppartementFragment.appartements = appartements;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = root.findViewById(R.id.listView);
        //list.setOnItemClickListener(this);
        setData();
        /*machineService = new MachineService(getActivity());
        MachineAdapter as = new MachineAdapter(getActivity(), machineService.findAll());
        Log.d("data",machineService.findAll().toString() );
        list.setAdapter(as);*/

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
                Type type = new TypeToken<Collection<Appartement>>() {
                }.getType();
                List<Appartement>data = new Gson().fromJson(response, type);
                Log.d("data", data.toString());
                AppartementFragment.setAppartements(data);
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
        this.list.setAdapter(new AppartementAdapter(getActivity(), appartements));
        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppartementFragment.position = position;
                Intent i = new Intent(getActivity(), UpdateAppartementActivity.class);
                i.putExtra("id",appartements.get(position).getId()+"");
                i.putExtra("etage",appartements.get(position).getEtage()+"");
                i.putExtra("lmmeuble",appartements.get(position).getLmmeuble()+"");
                i.putExtra("numero",appartements.get(position).getNumero()+"");
                i.putExtra("surface",appartements.get(position).getSurface()+"");

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