package com.example.footballleagueV2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AreaActivity extends AppCompatActivity {

    private ArrayList<String> compList = new ArrayList<>();

    JSONObject jsonObj;
    JSONArray jsonArray;

    ListView tvArea;
    RequestQueue requestQueue;
    String temppi;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        tvArea = findViewById(R.id.TextViewArea);

        Intent areaIntent = getIntent();
        String url = areaIntent.getStringExtra("url");

        requestQueue = Volley.newRequestQueue(this);

        getComp(url);
    }

    private void jsonParse(JSONArray json){
        for(int i = 0; i < json.length(); i++) {
            try {
                jsonObj = json.getJSONObject(i);
            } catch (JSONException e) {
                Log.d("keke2", "" + e);
            }

            try {
                temppi = jsonObj.getString("name");
            } catch (JSONException e) {
                Log.d("keke2", "" + e);
            }

            compList.add(temppi);
        }
        if(json.length() == 0) {
            compList.add("No leagues");
        }
        arrayAdappter();
    }

    private void getComp(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("competitions");
                            Log.d("keke3", "onResponse: " + jsonArray);
                            jsonParse(jsonArray);
                        }
                        catch(JSONException e) {
                            Log.d("keke3", "" + e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("keke3", "" + error);

                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    private void arrayAdappter() {
        final ArrayAdapter<String> mAdapter;
        mAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, compList);
        tvArea.setAdapter(mAdapter);
    }

}
