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

import ma.emsi.syndicapp.bean.Lmmeuble;

public class UpdateLmmeubleActivity extends AppCompatActivity {

    private static int lmmeuble_id;
    private EditText numero;
    private EditText nom;
    private EditText etage;
    private EditText addresse;
    private EditText ville;
    private Button update;
    private Button delete;
    private RequestQueue requestQueue;
    private String updateUrl = "http://192.168.56.1:8000/api/lmmeubles/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lmmeuble);

        nom = findViewById(R.id.nomI);
        etage = findViewById(R.id.etageI);
        numero = findViewById(R.id.numeroI);
        addresse = findViewById(R.id.addresseI);
        ville = findViewById(R.id.villeI);
        update = findViewById(R.id.updateLmmeubleBtn);
        delete = findViewById(R.id.deleteLmmeubleBtn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.lmmeuble_id = Integer.parseInt(extras.getString("id"));
            String nomD = extras.getString("nom");
            int etageD = Integer.parseInt(extras.getString("etage"));
            int numeroD = Integer.parseInt(extras.getString("numero"));
            String addresseD = extras.getString("addresse");
            String villeD = extras.getString("ville");

            nom.setText(nomD + "");
            etage.setText(etageD + "");
            numero.setText(numeroD + "");
            addresse.setText(addresseD+"");
            ville.setText(villeD + "");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lmmeuble imm = new Lmmeuble();

                imm.setEtage(Integer.parseInt(etage.getText().toString()));
                imm.setNumero(Integer.parseInt(numero.getText().toString()));
                imm.setNom(nom.getText().toString());
                imm.setAddresse(addresse.getText().toString());
                imm.setVille(ville.getText().toString());

                update(imm);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void update(Lmmeuble imm) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.PUT,
                updateUrl + lmmeuble_id + "/", new Response.Listener<String>() {
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

                params.put("nom", imm.getNom() + "");
                params.put("etage", imm.getEtage() + "");
                params.put("numero", imm.getNumero() + "");
                params.put("addresse", imm.getAddresse() + "");
                params.put("ville", imm.getAddresse() + "");
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void delete() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.DELETE,
                updateUrl + lmmeuble_id + "/", new Response.Listener<String>() {
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
                return params;
            }
        };
        requestQueue.add(request);
    }
}