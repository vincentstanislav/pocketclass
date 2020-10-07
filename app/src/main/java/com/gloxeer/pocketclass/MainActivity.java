package com.gloxeer.pocketclass;

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

//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
public class MainActivity extends AppCompatActivity {

        //declare variables
    private Button button;
    private TextView textView;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         }

  /* // @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
                button = (Button) findViewById(R.id.confirm);
                button.setText("changes");
                break;
            case R.id.textView:
                textView = (TextView) findViewById(R.id.textView);
                textView.setText("Clicked!");
                break;
            case R.id.card_sample:
                cardView = (CardView) findViewById(R.id.card_sample);
                textView.setText("Not Clicked!");
                break;
        }
    }*/
}