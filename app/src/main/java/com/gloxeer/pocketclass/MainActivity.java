package com.gloxeer.pocketclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//public class MainActivity extends AppCompatActivity {

        //declare variables
    private CardView mathCV;
    private CardView czechCV;
    private CardView quizCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mathCV = (CardView) findViewById(R.id.math);
        mathCV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startMath();
            }
        });
        //mathCV.performClick();

        czechCV = (CardView) findViewById(R.id.czech);
        czechCV.setOnClickListener(this);
        //czechCV.performClick();

         }

     private void startMath(){   //method to start math activity
        Intent intent = new Intent(MainActivity.this, MathActivity.class);
        startActivity(intent);
     }

   // @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.math:
                mathCV = (CardView) findViewById(R.id.math);
                Toast.makeText(this, "Math", Toast.LENGTH_SHORT).show();
                break;
            case R.id.czech:
                czechCV = (CardView) findViewById(R.id.czech);
                Toast.makeText(this, "Czech", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}