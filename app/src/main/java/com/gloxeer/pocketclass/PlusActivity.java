package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.gloxeer.pocketclass.databinding.ActivityPlusBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

public class PlusActivity extends AppCompatActivity implements View.OnClickListener{
    //MISSING - correct answers counter, questions generator, pass results to evaluation


    //VARIABLE DECLARATION
    private static final String TAG = "plusActivity_start";
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private static final int maxValue = 17;
    private int score = 0;
    private CardView cell0, cell1, cell2_1, cell2_2, cell2_3, cell3_1, cell3_2, cell3_3;
    private TextView actResult, cell1tw, cell2_1tw, cell2_2tw, cell2_3tw, cell3_1tw, cell3_2tw, cell3_3tw, textViewCountDown;
    private Button actionBtn;
    private Boolean answered;
    private int questionCntr;
    private int questionAmount = 2;
    private ArrayList <Integer> numbersList;
    private int numberListSize = 6;
    private int status; // 1-getQuestion, 2-getAnswered, 3-getEvaluation
    private int answer;
    private int resultPosition;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultCD; //MUSIM NADEFINOVAT
    private CountDownTimer countDownTimer;
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
        updateResult(); //initiate score

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "actionBtn.setOnClickListener :" + status + " , " + answered);
                    if (answered == true) {status = 2;}
                    switch (status) {
                        case 1:
                            if (countDownTimer != null){   //cancel countdown in case the timer is running
                                countDownTimer.cancel();}
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
                            getScorePercentage();
                            Intent intent = new Intent(PlusActivity.this,Evaluation.class);
                            intent.putExtra("scoreValue", score);
                            startActivity(intent);
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

        textViewCountDown = findViewById(R.id.text_view_countdown);
        actionBtn = findViewById(R.id.button);
        answered = false;
        status = 1;
        questionCntr = 0;
        numbersList = new ArrayList();
        answer = 0;

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

    //
    public void getScorePercentage() {
        score = (int) ((score * 100)/questionAmount);
    }
    //method to catch selected buttons
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick :"+v.getId());
        answered = false;
        switch (v.getId()){
            case R.id.cell2_1:
                setCellColor(cell2_1,2);
                answer = 1;
                break;
            case R.id.cell2_2:
                setCellColor(cell2_2,2);
                answer = 2;
                break;
            case R.id.cell2_3:
                setCellColor(cell2_3,2);
                answer = 3;
                break;
            case R.id.cell3_1:
                setCellColor(cell3_1,2);
                answer = 4;
                break;
            case R.id.cell3_2:
                setCellColor(cell3_2,2);
                answer = 5;
                break;
            case R.id.cell3_3:
                setCellColor(cell3_3,2);
                answer = 6;
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

    //get random number
    public int getRandomNumber(int number){
        return (int) (Math.random() * number);
    }

    //get next question
    private void getQuestion() {
        Log.d(TAG, "getQuestion()");

        // clear the color for all the cells
        setCellColor(cell2_1,1);
        setCellColor(cell2_2,1);
        setCellColor(cell2_3,1);
        setCellColor(cell3_1,1);
        setCellColor(cell3_2,1);
        setCellColor(cell3_3,1);

        //enable all the objcts for the selection
        enableActivityObjects();

        //generate question
        numbersList.clear(); //delete previous numbers list
        resultPosition = 0;
        int var1 =  getRandomNumber(maxValue);
        int var2 =  getRandomNumber(maxValue);
        int result = var1 + var2;

        //add result to the list
        numbersList.add(result);

        //generate list of unique results
        int uniqueNumber;
        Boolean isUnique = true;

        while (numbersList.size() < numberListSize + 1) {   //increased by the result on the first position
            uniqueNumber = getRandomNumber(maxValue) + getRandomNumber(maxValue); //generate rundom number

            for (int i = 0; i < numbersList.size(); i++) {
                    if (numbersList.get(i) == uniqueNumber) { //check if the number is unique
                        isUnique = false;
                        break;
                    }
                }
            if (isUnique == true) {numbersList.add(uniqueNumber);} // if the number is unique add it to the list
            isUnique = true;
        }

        //generate result position
        resultPosition = getRandomNumber(numberListSize);
        numbersList.remove(0);  //remove result from the list
        numbersList.remove(resultPosition); //remove number from result position
        numbersList.add(resultPosition, result); //add result to the proper position

        cell1tw.setText(String.valueOf(var1) + " + " + String.valueOf(var2) + " = ");
        cell2_1tw.setText(String.valueOf(numbersList.get(0)));
        cell2_2tw.setText(String.valueOf(numbersList.get(1)));
        cell2_3tw.setText(String.valueOf(numbersList.get(2)));
        cell3_1tw.setText(String.valueOf(numbersList.get(3)));
        cell3_2tw.setText(String.valueOf(numbersList.get(4)));
        cell3_3tw.setText(String.valueOf(numbersList.get(5)));

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    //get correct answer
    private void getAnswer() {
        Log.d(TAG, "getAnswer() ");
        answered = false;
        switch (resultPosition + 1){
            case 1:
                cell2_1.setCardBackgroundColor(GREEN);
                break;
            case 2:
                cell2_2.setCardBackgroundColor(GREEN);
                break;
            case 3:
                cell2_3.setCardBackgroundColor(GREEN);
                break;
            case 4:
                cell3_1.setCardBackgroundColor(GREEN);
                break;
            case 5:
                cell3_2.setCardBackgroundColor(GREEN);
                break;
            case 6:
                cell3_3.setCardBackgroundColor(GREEN);
                break;
        }

        //evaluate correct answer
        if (resultPosition + 1 == answer) {
            score++;
        }

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
        actResult.setText("Score: "+ String.valueOf(score) + " / " + String.valueOf(questionAmount));
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                System.out.println("task: " + String.valueOf("Finish!"));
                timeLeftInMillis = 0;
                updateCountDownText();
                getAnswer();
                /*actionBtn.setText("DALŠi");
                actionBtn.setEnabled(true);
                actionBtn.setVisibility(View.VISIBLE);*/
                diableActivityObjects();
                status = 1;
                updateResult();   //ADD METHOD FOR THE RESULTS
                if (questionCntr == questionAmount)
                {actionBtn.setText("VÝSLEDEK");
                    status = 3;}
                else
                {actionBtn.setText("DALšÍ");}
                //actionBtn.setEnabled(false);
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),   "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(RED);
        } else {
            textViewCountDown.setTextColor(WHITE);
        }

    }


}

