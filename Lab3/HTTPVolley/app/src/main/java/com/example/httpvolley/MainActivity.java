package com.example.httpvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;




public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Button btnGo;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.etPrint);
        btnGo = findViewById(R.id.buttonGo);
        mQueue = Volley.newRequestQueue(MainActivity.this);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }


    private void jsonParse() {
        final String url = "https://financialmodelingprep.com/api/company/price/AAPL,GOOGL,FB,NOK";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText(response);
                        mQueue.stop();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("Error getting text");
                error.printStackTrace();
                mQueue.stop();

            }
        });
        mQueue.add(stringRequest);

    }
}

