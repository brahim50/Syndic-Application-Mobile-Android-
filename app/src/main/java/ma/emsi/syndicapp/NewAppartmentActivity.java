package ma.emsi.syndicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import ma.emsi.syndicapp.bean.Appartement;

public class NewAppartmentActivity extends AppCompatActivity {

    private EditText lmmeuble;
    private EditText etage;
    private EditText numero;
    private EditText surface;
    private Button add;
    private RequestQueue requestQueue;
    private String addUrl = "http://192.168.56.1:8000/api/appartements";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appartment);

        lmmeuble = findViewById(R.id.lmmeuble);
        etage = findViewById(R.id.etage);
        numero = findViewById(R.id.numero);
        surface = findViewById(R.id.surface);
        add = findViewById(R.id.addAppartement);

        Bundle extras = getIntent().getExtras();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appartement app = new Appartement();
                app.setLmmeuble(lmmeuble.getText().toString());
                app.setEtage(Integer.parseInt(etage.getText().toString()));
                app.setNumero(Integer.parseInt(numero.getText().toString()));
                app.setSurface(Integer.parseInt(surface.getText().toString()));

                update(app);
            }
        });


    }

    private void update(Appartement app) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST,
                addUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "Done");
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error : " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("lmmeuble", app.getLmmeuble() + "");
                params.put("surface", app.getSurface() + "");
                params.put("etage", app.getEtage() + "");
                params.put("numero", app.getNumero() + "");
                return params;
            }
        };
        requestQueue.add(request);
    }

}