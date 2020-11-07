package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Evaluation extends AppCompatActivity {

    private int prog = 0;
    ProgressBar pb;
    Button bi, bd;
    TextView tw;
    private int scorePertantage;
    private int evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        bi = findViewById(R.id.button_incr);
        pb = findViewById(R.id.progress_bar);
        tw = findViewById(R.id.text_view_progress);

        scorePertantage = getIntent().getIntExtra("scoreValue", evaluation);
        updateProgressBar();


        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getBackToMath();
            }
        });

    }

    private void getBackToMath(){
        Intent intent = new Intent(Evaluation.this, MathActivity.class);
        startActivity(intent);
    }

    private void updateProgressBar(){
        System.out.println("scorePercantage: " + String.valueOf(scorePertantage));
        pb.setProgress(scorePertantage);
        tw.setText(Integer.valueOf(scorePertantage)+ "%");
    }
}