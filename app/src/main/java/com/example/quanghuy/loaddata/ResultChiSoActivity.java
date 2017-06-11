package com.example.quanghuy.loaddata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultChiSoActivity extends AppCompatActivity {

    TextView textViewIQ, textViewAQ, textViewCQ, textViewEQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_chi_so);
        textViewIQ = (TextView) findViewById(R.id.textViewIQ);
        textViewIQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iq = new Intent(ResultChiSoActivity.this, IQActivity.class);
                startActivity(iq);
            }
        });

        textViewAQ = (TextView) findViewById(R.id.textViewAQ);
        textViewAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aq = new Intent(ResultChiSoActivity.this, AQActivity.class);
                startActivity(aq);
            }
        });

        textViewCQ = (TextView) findViewById(R.id.textViewCQ);
        textViewCQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cq = new Intent(ResultChiSoActivity.this, CQActivity.class);
                startActivity(cq);
            }
        });

        textViewEQ = (TextView) findViewById(R.id.textViewEQ);
        textViewEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eq = new Intent(ResultChiSoActivity.this, EQActivity.class);
                startActivity(eq);
            }
        });


    }
}
