package com.example.footballleagueV2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AreaActivity extends AppCompatActivity {

    TextView tvArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        tvArea = findViewById(R.id.TextViewArea);



    }
}
