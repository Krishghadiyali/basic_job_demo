package com.example.crude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        EditText text = findViewById(R.id.updateText);
        String email = getIntent().getStringExtra("email");

         findViewById(R.id.updateBtn).setOnClickListener(v->{
             try {
                 String updatedtext = text.getText().toString();
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("email",email);
                 jsonObject.put("username",updatedtext);

                 aipclint.updateRequest(this,jsonObject,"update",response -> {
                     runOnUiThread(()->{
try {
    JSONObject json = new JSONObject(response);

    if (json.optBoolean("success",false)){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        Toast.makeText(this,"update successfully",Toast.LENGTH_SHORT).show();
    }
} catch (Exception e) {
    throw new RuntimeException(e);
}
                     });
                 });
             } catch (Exception e) {
                     throw new RuntimeException(e);
             }
         });
    }
}