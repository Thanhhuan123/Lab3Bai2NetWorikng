package com.example.lab3_bai2networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_volley);
        getJSON();

    }

    String jsonStr = null;
    public void getJSON()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/todos";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0;i<= response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String userId = object.getString("userId");
                        String id = object.getString("id");
                        String title = object.getString("title");

                        jsonStr +="userId: "+ userId+"\n\n";
                        jsonStr +="id: "+ id+"\n\n";
                        jsonStr +="title: "+ title+"\n\n";




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                textView.setText(jsonStr);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
        queue.add(request);

    }
}