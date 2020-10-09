package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MathActivity extends AppCompatActivity {

    private CardView multiplicationCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        multiplicationCV = (CardView) findViewById(R.id.multiplication);
        multiplicationCV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startMathMultiplication();
            }
        });
    }

    //method to start math multiplication activity
    private void startMathMultiplication(){
        Intent intent = new Intent(MathActivity.this, MathMultiplication.class);
        startActivity(intent);
    }

}