// ApiClient.java
package com.example.crude;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String baseurl = "http://192.168.43.57:3000/basic_job_demo/";
    public static void postRequest(Context context, String url, JSONObject json, ApiCallback callback) {
        RequestBody body = RequestBody.create(json.toString(), JSON);

        Request request = new Request.Builder()
                .url(baseurl+url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((AppCompatActivity) context).runOnUiThread(() ->
                        Toast.makeText(context, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                ((AppCompatActivity) context).runOnUiThread(() -> callback.onSuccess(res));
            }
        });
    }

    public static  void  getRequest (Context context, String url,  ApiCallback callback){

        Request request = new Request.Builder().
                url(baseurl+url).get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ((AppCompatActivity) context).runOnUiThread(()->Toast.makeText(context,"Faild:"+e.getMessage(),Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res = response.body().string();
                ((AppCompatActivity)context).runOnUiThread(()->callback.onSuccess(res));
            }
        });
    }

    public  static  void updateRequest(Context context,JSONObject jsonObject,String url,ApiCallback callback){
        RequestBody requestBody = RequestBody.create(jsonObject.toString() ,JSON);
        Request request = new Request.Builder().url(baseurl+url).put(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ((AppCompatActivity)context).runOnUiThread(()->{
                    Toast.makeText(context,"Faild:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res = response.body().string();
                ((AppCompatActivity)context).runOnUiThread(()->callback.onSuccess(res));
            }
        });
    }

    public static void deleteRequest(Context context, JSONObject jsonObject,String url,ApiCallback callback){
        RequestBody requestBody = RequestBody.create(jsonObject.toString(),JSON);
        Request request = new Request.Builder().url(baseurl+url).delete(requestBody).build();

      client.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(@NonNull Call call, @NonNull IOException e) {
              ((AppCompatActivity)context).runOnUiThread(()->{
                  Toast.makeText(context,"Faild:"+e.getMessage(),Toast.LENGTH_SHORT).show();
              });
          }

          @Override
          public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
              String res = response.body().string();
              ((AppCompatActivity)context).runOnUiThread(()->callback.onSuccess(res));
          }
      });
    }
    // callback interface
    public interface ApiCallback {
        void onSuccess(String response);
    }
}
