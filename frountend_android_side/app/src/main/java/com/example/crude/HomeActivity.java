package com.example.crude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
TextView text ;
Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        updatebtn = findViewById(R.id.Homeupdatebtn);
text = findViewById(R.id.Hometext);
        String email = getIntent().getStringExtra("email");

        updatebtn.setOnClickListener(v->{
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("email",email);
            startActivity(intent);
        });

        findViewById(R.id.Homedeletebtn).setOnClickListener(view -> {

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email",email);

                aipclint.deleteRequest(this,jsonObject,"delete",response -> {
                    runOnUiThread(()->{
                        try {
                            JSONObject object = new JSONObject(response);

                            if (object.optBoolean("success",false)){
                                Toast.makeText(this,"Account Deleted",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this, LoginActivity.class);
                                startActivity(intent);

                            }
                        } catch (Exception e) {
Toast.makeText(this,"delete Fail",Toast.LENGTH_SHORT).show();                        }
                    });
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        aipclint.getRequest(this,"getdata?email="+email,response -> {
            runOnUiThread(()->{
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject user = jsonObject.optJSONObject("user");
                    String username = user.optString("username","no name");
                    if (jsonObject.optBoolean("success",false)){
                        text.setText(username);
                    }
                } catch (Exception e) {
                    Toast.makeText(this,"usrname is empty",Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}