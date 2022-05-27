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

import ma.emsi.syndicapp.adapter.LmmeubleAdapter;
import ma.emsi.syndicapp.bean.Lmmeuble;
import ma.emsi.syndicapp.databinding.FragmentGalleryBinding;
import ma.emsi.syndicapp.ui.gallery.GalleryViewModel;


public class LmmeubleFragment extends Fragment {

    private static int id;
    private static int position;
    private ListView list;
    static private View v ;
    private FragmentGalleryBinding binding;
    static private List<Lmmeuble> lmmeubles;
    RequestQueue requestQueue;
    String getUrl = "http://192.168.56.1:8000/api/lmmeubles";

    public static List<Lmmeuble> getLmmeubles() {
        return lmmeubles;
    }

    public static void setLmmeubles(List<Lmmeuble> lmmeubles) {
        LmmeubleFragment.lmmeubles = lmmeubles;
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
                Type type = new TypeToken<Collection<Lmmeuble>>() {
                }.getType();
                List<Lmmeuble>data = new Gson().fromJson(response, type);
                Log.d("data", data.toString());
                LmmeubleFragment.setLmmeubles(data);
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
        this.list.setAdapter(new LmmeubleAdapter(getActivity(), lmmeubles));
        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LmmeubleFragment.position = position;
                Intent i = new Intent(getActivity(), UpdateLmmeubleActivity.class);
                i.putExtra("id",lmmeubles.get(position).getId()+"");
                i.putExtra("etage",lmmeubles.get(position).getEtage()+"");
                i.putExtra("ville",lmmeubles.get(position).getVille()+"");
                i.putExtra("numero",lmmeubles.get(position).getNumero()+"");
                i.putExtra("nom",lmmeubles.get(position).getNom()+"");
                i.putExtra("addresse",lmmeubles.get(position).getAddresse()+"");

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