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

public class NewLmmeubleActivity extends AppCompatActivity {

    private EditText numero;
    private EditText etage;
    private EditText nom;
    private EditText addresse;
    private EditText ville;
    private Button addLmmeubleBtn;
    private RequestQueue requestQueue;
    private String addUrl = "http://192.168.56.1:8000/api/lmmeubles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lmmeuble);

        nom = findViewById(R.id.nomI);
        etage = findViewById(R.id.etageI);
        numero = findViewById(R.id.numeroI);
        addresse = findViewById(R.id.addresseI);
        ville = findViewById(R.id.villeI);
        addLmmeubleBtn = findViewById(R.id.addLmmeubleBtn);

        Bundle extras = getIntent().getExtras();


        addLmmeubleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lmmeuble imm = new Lmmeuble();

                imm.setEtage(Integer.parseInt(etage.getText().toString()));
                imm.setNumero(Integer.parseInt(numero.getText().toString()));
                imm.setAddresse(addresse.getText().toString());
                imm.setNom(nom.getText().toString());
                imm.setVille(ville.getText().toString());


                addLmmeuble(imm);
            }
        });


    }

    private void addLmmeuble(Lmmeuble imm) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST,
                addUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ADDING", "Done");
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
                params.put("numero", imm.getNumero() + "");
                params.put("etage", imm.getEtage() + "");
                params.put("nom", imm.getNom() + "");
                params.put("addresse", imm.getAddresse() + "");
                params.put("ville", imm.getVille() + "");
                params.put("photo",  "none");
                return params;
            }
        };
        requestQueue.add(request);
    }

}