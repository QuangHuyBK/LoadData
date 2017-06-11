package com.example.quanghuy.loaddata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BackgroundActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textViewContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        imageView = (ImageView) findViewById(R.id.imageView);
        textViewContinue = (TextView) findViewById(R.id.textViewContinue);

        // quet rung
        final Vibrator vibe = (Vibrator) BackgroundActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                vibe.vibrate(500);
            }
        });

        //chuyen activity
        textViewContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh0 = new Intent(BackgroundActivity.this, LoginActivity.class);
                startActivity(mh0);
            }
        });
    }
}
