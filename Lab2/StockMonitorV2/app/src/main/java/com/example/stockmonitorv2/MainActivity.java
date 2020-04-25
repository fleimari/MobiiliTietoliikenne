package com.example.stockmonitorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn, btnAdd;
    public static TextView tvData;
    public static EditText etID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonGet);
        btnAdd = findViewById(R.id.buttonAdd);
        tvData = findViewById(R.id.text_view);
        etID = findViewById(R.id.edit_ID);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData process = new fetchData();
                process.execute();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String stockAdd = etID.getText().toString();

                addData newAdd = new addData();
                newAdd.execute();
            }
        });
    }
}
