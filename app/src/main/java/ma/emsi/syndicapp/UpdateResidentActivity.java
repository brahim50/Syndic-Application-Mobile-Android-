package ma.emsi.syndicapp;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashMap;
import java.util.Map;

import ma.emsi.syndicapp.bean.Resident;
import ma.emsi.syndicapp.databinding.ActivityUpdateResidentBinding;

public class UpdateResidentActivity extends AppCompatActivity {

    private EditText lmmeuble;
    private EditText appartement;
    private EditText nom;
    private EditText prenom;
    private EditText ville;
    private EditText email;
    private EditText tele;
    private Button update;
    private Button delete;
    private RequestQueue requestQueue;
    private String addUrl = "http://192.168.56.1:8000/api/residents/";
    private AppBarConfiguration appBarConfiguration;
    private ActivityUpdateResidentBinding binding;
    private int resident_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateResidentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nom = findViewById(R.id.nomR);
        lmmeuble = findViewById(R.id.lmmeubleR);
        appartement = findViewById(R.id.appartementR);
        prenom = findViewById(R.id.prenomR);
        ville = findViewById(R.id.villeR);
        tele = findViewById(R.id.teleR);
        email = findViewById(R.id.emailR);
        update = findViewById(R.id.updateResidentBtn);
        delete = findViewById(R.id.deleteResidentBtn);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.resident_id = Integer.parseInt(extras.getString("id"));
            String nomD = extras.getString("nom");
            String lmmeubleD = extras.getString("lmmeuble");
            String appartementD = extras.getString("appartement");
            String prenomD = extras.getString("prenom");
            String villeD = extras.getString("ville");
            String emailD = extras.getString("email");
            String teleD = extras.getString("tele");


            lmmeuble.setText(lmmeubleD + "");
            nom.setText(nomD  + "");
            appartement.setText(appartementD + "");
            prenom.setText(prenomD + "");
            ville.setText(villeD  + "");
            email.setText(emailD  + "");
            tele.setText(teleD  + "");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resident r = new Resident();

                r.setLmmeuble(lmmeuble.getText().toString());
                r.setNom(nom.getText().toString());
                r.setAppartement(appartement.getText().toString());
                r.setVille(ville.getText().toString());
                r.setEmail(email.getText().toString());
                r.setTele(tele.getText().toString());

                update(r);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

    }

    private void delete() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.DELETE,
                addUrl + resident_id + "/", new Response.Listener<String>() {
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

    private void update(Resident r) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.PUT,
                addUrl + resident_id + "/", new Response.Listener<String>() {
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

                params.put("lmmeuble", r.getLmmeuble() + "");
                params.put("nom", r.getNom() + "");
                params.put("prenom", r.getPrenom() + "");
                params.put("ville", r.getVille() + "");
                params.put("email", r.getEmail() + "");
                params.put("tele", r.getTele() + "");
                params.put("appartement", r.getAppartement() + "");



                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_update_resident);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}