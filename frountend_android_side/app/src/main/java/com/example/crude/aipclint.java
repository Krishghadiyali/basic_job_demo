// ApiClient.java
package com.example.crude;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class aipclint {

    private static final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void postRequest(Context context, String url, JSONObject json, ApiCallback callback) {
        RequestBody body = RequestBody.create(json.toString(), JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((MainActivity) context).runOnUiThread(() ->
                        Toast.makeText(context, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                ((MainActivity) context).runOnUiThread(() -> callback.onSuccess(res));
            }
        });
    }

    // callback interface
    public interface ApiCallback {
        void onSuccess(String response);
    }
}
