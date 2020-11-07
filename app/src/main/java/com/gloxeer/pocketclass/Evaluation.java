package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        bd = findViewById(R.id.button_decr);
        bi = findViewById(R.id.button_incr);
        pb = findViewById(R.id.progress_bar);
        tw = findViewById(R.id.text_vire_ptogress);

        updateProgressBar();

        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prog <= 90){
                    prog += 10;
                    updateProgressBar();
                }
            }
        });

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prog >= 10){
                    prog -= 10;
                    updateProgressBar();
                }
            }
        });
    }

    private void updateProgressBar(){
        pb.setProgress(prog);
        tw.setText(Integer.valueOf(prog)+ "%");
    }
}