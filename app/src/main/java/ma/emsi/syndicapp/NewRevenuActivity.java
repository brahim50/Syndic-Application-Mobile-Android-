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

public class NewRevenuActivity extends AppCompatActivity  implements View.OnClickListener {


    private EditText lmmeuble;
    private EditText appartement;
    private EditText somme;
    private EditText date;

    private Button addRevenuB;
    private RequestQueue requestQueue;
    private String addUrl = "http://192.168.56.1:8000/api/revenus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_revenu);

        lmmeuble = findViewById(R.id.lmmeubleRv);
        appartement = findViewById(R.id.appartementRv);
        somme = findViewById(R.id.sommeRv);
        date = findViewById(R.id.dateRv);
        addRevenuB = findViewById(R.id.addRevenuB);

        Bundle extras = getIntent().getExtras();



        addRevenuB.setOnClickListener(this);


    }

    private void addRevenu(Revenu r) {
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
                params.put("somme", r.getSomme() + "");
                params.put("date", r.getDate() + "");
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void onClick(View view) {
        if(view == addRevenuB) {


        Log.i("clk", "onClick: rv");

        Revenu r = new Revenu();

        r.setAppartement(Integer.parseInt(appartement.getText().toString()));
        r.setLmmeuble(lmmeuble.getText().toString());
        r.setSomme(Integer.parseInt(somme.getText().toString()));
        r.setDate(date.getText().toString());
        addRevenu(r);
        }
    }
}