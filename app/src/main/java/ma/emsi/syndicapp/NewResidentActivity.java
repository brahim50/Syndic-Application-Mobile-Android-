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

import ma.emsi.syndicapp.bean.Resident;

public class NewResidentActivity extends AppCompatActivity {

    private EditText lmmeuble;
    private EditText appartement;
    private EditText nom;
    private EditText prenom;
    private EditText ville;
    private EditText email;
    private EditText tele;
    private Button addResidentBtn;
    private RequestQueue requestQueue;
    private String addUrl = "http://192.168.56.1:8000/api/residents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_resident);

        nom = findViewById(R.id.nomR);
        lmmeuble = findViewById(R.id.lmmeubleR);
        appartement = findViewById(R.id.appartementR);
        prenom = findViewById(R.id.prenomR);
        ville = findViewById(R.id.villeR);
        tele = findViewById(R.id.appartementR);
        email = findViewById(R.id.prenomR);
        addResidentBtn = findViewById(R.id.addResidentBtn);

        Bundle extras = getIntent().getExtras();


        addResidentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resident r = new Resident();

                r.setAppartement(appartement.getText().toString());
                r.setEmail(email.getText().toString());
                r.setNom(nom.getText().toString());
                r.setPrenom(prenom.getText().toString());
                r.setTele(tele.getText().toString());
                r.setVille(ville.getText().toString());
                r.setLmmeuble(lmmeuble.getText().toString());

                addResident(r);
            }
        });


    }

    private void addResident(Resident r) {
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
                params.put("lmmeuble", r.getLmmeuble() + "");
                params.put("appartement", r.getAppartement() + "");
                params.put("nom", r.getNom() + "");
                params.put("prenom", r.getPrenom() + "");
                params.put("ville", r.getVille() + "");
                params.put("email", r.getEmail() + "");
                params.put("tele", r.getTele() + "");
                params.put("photo",  "none");
                return params;
            }
        };
        requestQueue.add(request);
    }

}