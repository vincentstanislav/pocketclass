package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.gloxeer.pocketclass.databinding.ActivityPlusBinding;

import java.util.Random;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;


public class PlusActivity extends AppCompatActivity implements View.OnClickListener{
    //VARIABLE DECLARATION
    private static final String TAG = "plusActivity_start";
    CardView cell1, cell2_1, cell2_2, cell2_3, cell3_1, cell3_2, cell3_3;
    TextView cell1tw, cell2_1tw, cell2_2tw, cell2_3tw, cell3_1tw, cell3_2tw, cell3_3tw;
    Button actionBtn;
    Boolean answered;
    int questionCntr;
    int status; // 1-new question, 2-answered, 3-evaluated
    int random;
    //LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        //set binding
        /*ActivityPlusBinding binding = ActivityPlusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setText("AHOJ");
        */

        //VARIABLE INITIATION
        varInit();
        onClickInit();
        hideActivityElements(View.GONE); //hide element at the beginning

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "actionBtn.setOnClickListener :"+status);

                    switch (status) {
                        case 1:
                            if (questionCntr == 0){hideActivityElements(View.VISIBLE);}
                            getQuestion();
                            status = 2;
                            actionBtn.setText("ODPOVĚZ");
                            break;
                        case 2:

                            status = 3;
                            break;
                        case 3:
                            break;

                    }

            }
        });
    }

    //METHODS DEFINITION

    private void varInit(){
        cell1 = findViewById(R.id.cell1);
        cell2_1 = findViewById(R.id.cell2_1);
        cell2_2 = findViewById(R.id.cell2_2);
        cell2_3 = findViewById(R.id.cell2_3);
        cell3_1 = findViewById(R.id.cell3_1);
        cell3_2 = findViewById(R.id.cell3_2);
        cell3_3 = findViewById(R.id.cell3_3);

        cell1tw = findViewById(R.id.cell1tw);
        cell2_1tw = findViewById(R.id.cell2_1tw);
        cell2_2tw = findViewById(R.id.cell2_2tw);
        cell2_3tw = findViewById(R.id.cell2_3tw);
        cell3_1tw = findViewById(R.id.cell3_1tw);
        cell3_2tw = findViewById(R.id.cell3_2tw);
        cell3_3tw = findViewById(R.id.cell3_3tw);

        actionBtn = findViewById(R.id.button);
        answered = false;
        status = 1;
        questionCntr = 0;

    }

    //make activity objects clickable
    private void onClickInit() {
        cell1.setOnClickListener(this);
        cell2_1.setOnClickListener(this);
        cell2_2.setOnClickListener(this);
        cell2_3.setOnClickListener(this);
        cell3_1.setOnClickListener(this);
        cell3_2.setOnClickListener(this);
        cell3_3.setOnClickListener(this);
        actionBtn.setOnClickListener(this);
    }

    //method to catch selected buttons
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick :"+v.getId());
        answered = false;
        switch (v.getId()){
            case R.id.cell2_1:
                setSelectedCellColor(cell2_1);
                break;
            case R.id.cell2_2:
                setSelectedCellColor(cell2_2);
                break;
            case R.id.cell2_3:
                setSelectedCellColor(cell2_3);
                break;
            case R.id.cell3_1:
                setSelectedCellColor(cell3_1);
                break;
            case R.id.cell3_2:
                setSelectedCellColor(cell3_2);
                break;
            case R.id.cell3_3:
                setSelectedCellColor(cell3_3);
                break;
            default:
                //throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    //set selected cell background color
    private void setSelectedCellColor (CardView cardView) {
        cell2_1.setCardBackgroundColor(WHITE);
        cell2_2.setCardBackgroundColor(WHITE);
        cell2_3.setCardBackgroundColor(WHITE);
        cell3_1.setCardBackgroundColor(WHITE);
        cell3_2.setCardBackgroundColor(WHITE);
        cell3_3.setCardBackgroundColor(WHITE);
        Log.d(TAG, "onClick1 :"+cardView);
        cardView.setCardBackgroundColor(Color.YELLOW);
    }

    //hide elements
    private void hideActivityElements(int select) {
        cell2_1.setVisibility(select);
        cell2_2.setVisibility(select);
        cell2_3.setVisibility(select);
        cell3_1.setVisibility(select);
        cell3_2.setVisibility(select);
        cell3_3.setVisibility(select);
    }

    //get next question
    private void getQuestion() {
        random = (int) (Math.random() * 15);
        cell1tw.setText("21 + 58 = ");
        cell2_1tw.setText(String.valueOf(random));
        cell2_2tw.setText(String.valueOf(random));
        cell2_3tw.setText(String.valueOf(random));
        cell3_1tw.setText(String.valueOf(random));
        cell3_2tw.setText(String.valueOf(random));
        cell3_3tw.setText(String.valueOf(random));
    }


}