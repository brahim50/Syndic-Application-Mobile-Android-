package ma.emsi.syndicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class );
                //startActivity(intent);

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(AuthActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    String userr = username.getText().toString();
                    String pass = password.getText().toString();
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    Toast.makeText(AuthActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }
            }
        });
    }
}