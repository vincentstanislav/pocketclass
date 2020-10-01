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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //declare variables
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.confirm);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:

                return true;

            case R.id.favorite:

                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

   // @Override
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
        }
    }
}