package com.example.crude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText email , password;
    Button btn , signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        btn = findViewById(R.id.loginbtn);
signup = findViewById(R.id.logintosignupbtn);
        btn.setOnClickListener(v -> loginUser());
signup.setOnClickListener(v->{
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
});
    }

   private void loginUser(){

        try {
           String inputemail = email.getText().toString();
            JSONObject jsonObject = new JSONObject();
jsonObject.put("email",inputemail);
            jsonObject.put("password",password.getText().toString());

            aipclint.postRequest(this,"login",jsonObject,response -> {
runOnUiThread(()->{
    try {
        // Convert response string to JSON for checking
        JSONObject resjson = new JSONObject(response);

        if (resjson.optBoolean("success",false)){
            Toast.makeText(this,"login successfull:",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("email",inputemail);
            startActivity(intent);
        }
    } catch (Exception e) {
        Toast.makeText(this, "login failed:"+response, Toast.LENGTH_SHORT).show();
    }
});
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}