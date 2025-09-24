package com.example.crude;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
EditText username , email , password;
Button signup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username =findViewById(R.id.signupUserName);
        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPassword);
        signup =findViewById(R.id.signupBtn);

        signup.setOnClickListener(view -> signupUser());

    }

    private void signupUser(){
        String jUsername = username.getText().toString();
        String jEmail = email.getText().toString();
        String jPassword = password.getText().toString();

        try {
            JSONObject json = new JSONObject();
            json.put("username",jUsername);
            json.put("email",jEmail);
            json.put("password",jPassword);

            aipclint.postRequest(this,"signup",json,response -> {
Toast.makeText(this,"signup res"+response,Toast.LENGTH_SHORT).show();
            });
        }catch (Exception e){
e.printStackTrace();
        }
    }
}