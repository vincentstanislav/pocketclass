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
    //MISSING - correct answers counter, questions generator, pass results to evaluation


    //VARIABLE DECLARATION
    private static final String TAG = "plusActivity_start";
    CardView cell0, cell1, cell2_1, cell2_2, cell2_3, cell3_1, cell3_2, cell3_3;
    TextView actResult, cell1tw, cell2_1tw, cell2_2tw, cell2_3tw, cell3_1tw, cell3_2tw, cell3_3tw;
    Button actionBtn;
    Boolean answered;
    int questionCntr;
    int questionAmount = 2;
    int status; // 1-getQuestion, 2-getAnswered, 3-getEvaluation
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
                Log.d(TAG, "actionBtn.setOnClickListener :" + status + " , " + answered);
                    if (answered == true) {status = 2;}
                    switch (status) {
                        case 1:
                            if (questionCntr == 0){hideActivityElements(View.VISIBLE);} //display activity objects
                            questionCntr++;
                            getQuestion();
                            enableActivityObjects();
                            status = 2;
                            actionBtn.setText("POTVRĎ");
                            break;
                        case 2:
                            getAnswer();
                            diableActivityObjects();
                            status = 1;
                            updateResult();   //ADD METHOD FOR THE RESULTS
                            if (questionCntr == questionAmount)
                                    {actionBtn.setText("VÝSLEDEK");
                                     status = 3;}
                                else
                                    {actionBtn.setText("DALšÍ");}
                            //actionBtn.setEnabled(false);
                            break;
                        case 3:
                            finish();      //CHANGE BY REDIRECT TO RESULTS
                            break;

                    }

            }
        });
    }

    //METHODS DEFINITION

    private void varInit(){
        Log.d(TAG, "varInit()");
        cell0 = findViewById(R.id.cell0);
        cell1 = findViewById(R.id.cell1);
        cell2_1 = findViewById(R.id.cell2_1);
        cell2_2 = findViewById(R.id.cell2_2);
        cell2_3 = findViewById(R.id.cell2_3);
        cell3_1 = findViewById(R.id.cell3_1);
        cell3_2 = findViewById(R.id.cell3_2);
        cell3_3 = findViewById(R.id.cell3_3);

        actResult = findViewById(R.id.actResult);
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
        Log.d(TAG, "onClickInit()");
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
                setCellColor(cell2_1,2);
                break;
            case R.id.cell2_2:
                setCellColor(cell2_2,2);
                break;
            case R.id.cell2_3:
                setCellColor(cell2_3,2);
                break;
            case R.id.cell3_1:
                setCellColor(cell3_1,2);
                break;
            case R.id.cell3_2:
                setCellColor(cell3_2,2);
                break;
            case R.id.cell3_3:
                setCellColor(cell3_3,2);
                break;
            default:
                //throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    //set selected cell background color, type: 1 deletes all cells to default color, type2: selects highlights selection
    private void setCellColor (CardView cardView, int type) {
        Log.d(TAG, "setCellColor()");
        answered = true;
        cell2_1.setCardBackgroundColor(WHITE);
        cell2_2.setCardBackgroundColor(WHITE);
        cell2_3.setCardBackgroundColor(WHITE);
        cell3_1.setCardBackgroundColor(WHITE);
        cell3_2.setCardBackgroundColor(WHITE);
        cell3_3.setCardBackgroundColor(WHITE);

        if (type == 2) { cardView.setCardBackgroundColor(Color.YELLOW);}
    }

    //hide elements
    private void hideActivityElements(int select) {
        Log.d(TAG, "hideActivityElements");
        cell2_1.setVisibility(select);
        cell2_2.setVisibility(select);
        cell2_3.setVisibility(select);
        cell3_1.setVisibility(select);
        cell3_2.setVisibility(select);
        cell3_3.setVisibility(select);
    }

    //get next question
    private void getQuestion() {
        Log.d(TAG, "getQuestion()");
        setCellColor(cell2_1,1);  // NEED TO CLEAR COLOR TO ALL CELLS
        cell2_1.setEnabled(true);       // NEED TO ENABLE ALL OBJECTS
        random = (int) (Math.random() * 15);
        cell1tw.setText("21 + 58 = ");
        cell2_1tw.setText(String.valueOf(random));
        cell2_2tw.setText(String.valueOf(random));
        cell2_3tw.setText(String.valueOf(random));
        cell3_1tw.setText(String.valueOf(random));
        cell3_2tw.setText(String.valueOf(random));
        cell3_3tw.setText(String.valueOf(random));
    }

    //get correct answer
    private void getAnswer() {
        Log.d(TAG, "getAnswer() ");
        answered = false;
        cell2_1.setCardBackgroundColor(GREEN);
    }

    private void diableActivityObjects() {
        cell2_1.setEnabled(false);
        cell2_2.setEnabled(false);
        cell2_3.setEnabled(false);
        cell3_1.setEnabled(false);
        cell3_2.setEnabled(false);
        cell3_3.setEnabled(false);
    }

    private void enableActivityObjects() {
        cell2_1.setEnabled(true);
        cell2_2.setEnabled(true);
        cell2_3.setEnabled(true);
        cell3_1.setEnabled(true);
        cell3_2.setEnabled(true);
        cell3_3.setEnabled(true);
    }

    private void updateResult() {
        actResult.setText("Score: "+ String.valueOf(questionCntr) + " / " + String.valueOf(questionAmount));
    }


}