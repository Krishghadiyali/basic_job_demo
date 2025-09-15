package com.example.crude;

import android.content.Context;
import android.widget.Toast;

public class validation {
    private static boolean validateSignup(String username, String email, String password, Context context) {
        if (username.isEmpty()) {
            Toast.makeText(context, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty() || password.length() < 6) {
            Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
