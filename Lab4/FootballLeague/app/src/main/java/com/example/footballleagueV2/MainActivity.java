package com.example.footballleagueV2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    public String jsonUrl = "http://api.football-data.org/v2/areas";
    public ListView mListView;
    Button btnGet;

    private RequestQueue mQueue;

    LeagueListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        mQueue = Volley.newRequestQueue(this);

        adapter = new LeagueListAdapter(this, R.layout.adapter_view_layout, League.getLeagueList());

        btnGet = findViewById(R.id.buttonGet);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                League gLeague = new League();

                String areaName = gLeague.getLeagueName();
                int areaID = gLeague.getLeagueID();
                String areaUrl = "http://api.football-data.org/v2/competitions?areas=" + areaID;
                Log.d("areaUrl", areaUrl + " Name: " + areaName);
            }
        });
        

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                Log.d("tagLIST", League.getLeagueList().toString());
            }
        });
    }

    private void jsonParse() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("areas");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject areas = jsonArray.getJSONObject(i);

                                String name = areas.getString("name");
                                int nameID = areas.getInt("id");

                                League mLeague = new League(name, nameID);
                                mLeague.addLeagueList(mLeague);
                                Log.d("area", mLeague.getLeagueName() + mLeague.getLeagueID());
                            }
                            mListView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }


}
