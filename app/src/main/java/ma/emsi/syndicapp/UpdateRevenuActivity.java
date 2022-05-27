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

import ma.emsi.syndicapp.bean.Revenu;

public class UpdateRevenuActivity extends AppCompatActivity {

    private EditText lmmeuble;
    private EditText appartement;
    private EditText somme;
    private EditText date;

    private Button updateRevenuB;
    private Button deleteRevenuB;
    private RequestQueue requestQueue;
    private String addUrl = "http://192.168.56.1:8000/api/revenus/";
    private int revenu_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_revenu);

        lmmeuble = findViewById(R.id.lmmeubleRv);
        appartement = findViewById(R.id.appartementRv);
        somme = findViewById(R.id.sommeRv);
        date = findViewById(R.id.dateRv);
        updateRevenuB = findViewById(R.id.updateRevenuB);
        deleteRevenuB = findViewById(R.id.deleteRevenuB);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.revenu_id = Integer.parseInt(extras.getString("id"));
            String lmmeubleD = extras.getString("lmmeuble");
            String appartementD = extras.getString("appartement");
            String sommeD = extras.getString("somme");
            String dateD = extras.getString("date");

            lmmeuble.setText(lmmeubleD + "");
            appartement.setText(appartementD + "");
            somme.setText(sommeD + "");
            date.setText(dateD + "");
        }

        updateRevenuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Revenu r = new Revenu();

                r.setLmmeuble(lmmeuble.getText().toString());
                r.setAppartement(Integer.parseInt(appartement.getText().toString()));
                r.setSomme(Integer.parseInt(somme.getText().toString()));
                r.setDate(date.getText().toString());

                update(r);
            }
        });

        deleteRevenuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });


    }

    private void delete() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.DELETE,
                addUrl + revenu_id + "/", new Response.Listener<String>() {
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

    private void update(Revenu r) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.PUT,
                addUrl + revenu_id + "/", new Response.Listener<String>() {
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
                params.put("date", r.getDate() + "");
                params.put("somme", r.getSomme() + "");
                params.put("appartement", r.getAppartement() + "");
                return params;
            }
        };
        requestQueue.add(request);
    }
}