package com.example.footballleagueV2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    ArrayList<League> leagueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        mQueue = Volley.newRequestQueue(this);



        btnGet = findViewById(R.id.buttonGet);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

        LeagueListAdapter adapter = new LeagueListAdapter(this, R.layout.adapter_view_layout, leagueList);
        mListView.setAdapter(adapter);

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

                                int nameID = areas.getInt("id");
                                String name = areas.getString("name");

                                League mLeague = new League(name, nameID);
                                leagueList.add(mLeague);
                            }

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
