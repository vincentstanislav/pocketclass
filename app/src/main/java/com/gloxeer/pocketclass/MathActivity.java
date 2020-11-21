package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MathActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView multiplicationCV;
    private CardView plusCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        //initiate variables
        multiplicationCV = (CardView) findViewById(R.id.multiplication);
        plusCV = (CardView) findViewById(R.id.plus);

        //set on click listener
        multiplicationCV.setOnClickListener(this);
        plusCV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){ //set on click listener for view objects
        Intent intent;

        switch (v.getId()) {
            case R.id.plus:
                intent = new Intent(MathActivity.this, PlusActivity.class);
                break;
            case R.id.multiplication:
                intent = new Intent(MathActivity.this, MathMultiplication.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        startActivity(intent);

    }

}