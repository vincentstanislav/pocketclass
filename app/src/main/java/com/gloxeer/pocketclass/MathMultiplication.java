package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MathMultiplication extends AppCompatActivity implements View.OnClickListener{

    //declare variable
    private static final String TAG = "MMultiplicationActivity";
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private static final int MAX = 15;

    private TextView cd1, cd2, cd3, cd4, cd5, cd6, result;
    private CardView ccd1, ccd2, ccd3, ccd4, ccd5, ccd6, cresult;
    private int selectionId;
    private int score = 0;
    private int questionCnt = 0;
    private int scorePercentage;
    int res, corAnswerPos, a, b;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private Button nextButton;
    private ArrayList<Integer> arrList = new ArrayList<>();
    private GridLayout gl;
    private CountDownTimer countDownTimer;
    private TextView textViewCountDown;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultCD;

    MathRandomSix task;
    int clickCount;
    int questionsAmount = 3;
    int questionCounter = 0;
    boolean answered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_multiplication);

        //assign variables to layout objects
        cd1 = findViewById(R.id.text_math_cd1);
        cd2 = findViewById(R.id.text_math_cd2);
        cd3 = findViewById(R.id.text_math_cd3);
        cd4 = findViewById(R.id.text_math_cd4);
        cd5 = findViewById(R.id.text_math_cd5);
        cd6 = findViewById(R.id.text_math_cd6);
        result = findViewById(R.id.text_math_result);

        ccd1 = findViewById(R.id.math_cd1);
        ccd2 = findViewById(R.id.math_cd2);
        ccd3 = findViewById(R.id.math_cd3);
        ccd4 = findViewById(R.id.math_cd4);
        ccd5 = findViewById(R.id.math_cd5);
        ccd6 = findViewById(R.id.math_cd6);
        cresult = findViewById(R.id.math_result);

        textViewCountDown = findViewById(R.id.text_view_countdown);
        textColorDefaultCD =textViewCountDown.getTextColors();

        textViewQuestionCount = findViewById(R.id.text_view_questions_count);
        textViewScore = findViewById(R.id.text_view_score);

        nextButton = findViewById(R.id.math_button);
        gl = findViewById(R.id.grid_layout);

        //loads initial screen
        assignToLayoutFirst();

        //set on click listener onClick method
        ccd1.setOnClickListener(this);
        ccd2.setOnClickListener(this);
        ccd3.setOnClickListener(this);
        ccd4.setOnClickListener(this);
        ccd5.setOnClickListener(this);
        ccd6.setOnClickListener(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "nextButton.setOnClick");
                displayAllFields();
                if (answered) {
                    showSolution();
                    answered = false;
                } else {
                    if (countDownTimer != null){   //cancel countdown in case the timer is running
                        countDownTimer.cancel();}
                    questionCounter++;
                    nextQuestion();
                }
            }
        });
    }

    // @Override
    public void onClick(View v) {
        System.out.println("geID_start: " + String.valueOf(v.getId()));

        setDefaultColors();
        selectionId = 0;

       switch (v.getId()) {
            case R.id.math_cd1:
                ccd1.setCardBackgroundColor(Color.YELLOW);
                selectionId = 1;
                break;
            case R.id.math_cd2:
                ccd2.setCardBackgroundColor(Color.YELLOW);
                selectionId = 2;
                break;
            case R.id.math_cd3:
                ccd3.setCardBackgroundColor(Color.YELLOW);
                selectionId = 3;
                break;
            case R.id.math_cd4:
                ccd4.setCardBackgroundColor(Color.YELLOW);
                selectionId = 4;
                break;
            case R.id.math_cd5:
                selectionId = 5;
                ccd5.setCardBackgroundColor(Color.YELLOW);
                break;
            case R.id.math_cd6:
                selectionId = 6;
                ccd6.setCardBackgroundColor(Color.YELLOW);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        nextButton.setText("POTVRĎ");
        answered = true;

        nextButton.setText("POTVRĎ");
        nextButton.setEnabled(true);

        nextButton.setVisibility(View.VISIBLE);

        System.out.println("geID_finish: " + String.valueOf(selectionId));
    }

    private void scoreCount(){
        if (selectionId == corAnswerPos) score++;
    }

    private void nextQuestion(){
        questionCnt++;
        if (questionCounter == questionsAmount){
            getScorePercentage();
            //finish();
            System.out.println("socre passed: " + String.valueOf(scorePercentage) + " " + String.valueOf(score) + " " + String.valueOf(questionCnt));
            Intent intent = new Intent(MathMultiplication.this, Evaluation.class);
            intent.putExtra("scoreValue", scorePercentage);
            startActivity(intent);
        }
        else {
            setDefaultColors();
            enableObjects(true);
            getQuestion();
            assignToLayout();
        }
    }

    public void getScorePercentage() {
        scorePercentage = (int) ((score * 100)/questionCnt);
    }

    public void showSolution(){

        System.out.println("task: " + String.valueOf("CorrAnswer2: "+ corAnswerPos));
        switch (corAnswerPos){
            case 1:
                ccd1.setCardBackgroundColor(Color.GREEN);
                break;
            case 2:
                ccd2.setCardBackgroundColor(Color.GREEN);
                break;
            case 3:
                ccd3.setCardBackgroundColor(Color.GREEN);
                break;
            case 4:
                ccd4.setCardBackgroundColor(Color.GREEN);
                break;
            case 5:
                ccd5.setCardBackgroundColor(Color.GREEN);
                break;
            case 6:
                ccd6.setCardBackgroundColor(Color.GREEN);
                break;
        }
        enableObjects(false);
        result.setText(a + " * " + b + " = " + String.valueOf(task.getResult()));
        scoreCount();
        corAnswerPos = 0;

        if (questionCounter == questionsAmount-1){
            nextButton.setText("HODNOCENÍ");
        } else {
            nextButton.setText("DALŠÍ");
        }

      // return results
    }

    private void setDefaultColors(){
        ccd1.setCardBackgroundColor(Color.WHITE);
        ccd2.setCardBackgroundColor(Color.WHITE);
        ccd3.setCardBackgroundColor(Color.WHITE);
        ccd4.setCardBackgroundColor(Color.WHITE);
        ccd5.setCardBackgroundColor(Color.WHITE);
        ccd6.setCardBackgroundColor(Color.WHITE);
    }

    private void assignToLayoutFirst(){
        Log.d(TAG, "assignToLayoutFirst");
        // hide all the fields
        score = 0;
        scorePercentage = 0;
        questionCnt = 0;

        ccd1.setVisibility(View.GONE);
        ccd2.setVisibility(View.GONE);
        ccd3.setVisibility(View.GONE);
        ccd4.setVisibility(View.GONE);
        ccd5.setVisibility(View.GONE);
        ccd6.setVisibility(View.GONE);
        cresult.setVisibility(View.GONE);
        //set starting text to the button

        nextButton.setText("ZAČNI");
    }

    private void enableObjects(boolean value){
        ccd1.setEnabled(value);
        ccd2.setEnabled(value);
        ccd3.setEnabled(value);
        ccd4.setEnabled(value);
        ccd5.setEnabled(value);
        ccd6.setEnabled(value);
    }

    private void displayAllFields(){
        Log.d(TAG, "displayAllFields");
        // display all the fields
        ccd1.setVisibility(View.VISIBLE);
        ccd2.setVisibility(View.VISIBLE);
        ccd3.setVisibility(View.VISIBLE);
        ccd4.setVisibility(View.VISIBLE);
        ccd5.setVisibility(View.VISIBLE);
        ccd6.setVisibility(View.VISIBLE);
        cresult.setVisibility(View.VISIBLE);
    }

    private void getQuestion(){
        Log.d(TAG, "getQuestion");

        int numbersCount = 6;
        int i = 1;
        int nb1, nb2, resNumber = 0;
        boolean repetition = true;

        a = (int) (Math.random() * MAX);
        b = (int) (Math.random() * MAX);
        res = a * b;

        corAnswerPos = (int) (Math.floor(Math.random() * numbersCount) + 1);
        arrList.add(res);

        while (i < numbersCount){

                while (repetition == true) {
                    nb1 = generateRandomInt();
                    nb2 = generateRandomInt();
                    resNumber = nb1 * nb2;
                    //arrList.add(resNumber);

                    for (int j = 0; j < i; j++) {
                        if (arrList.get(j) == resNumber){
                           // System.out.println("j, newres: " + String.valueOf(j) + "," + String.valueOf(resNumber));
                            break;
                        }
                        else if (j == i-1) {repetition = false;}
                    }

                }
                arrList.add(resNumber);
               // while (repetition = false) {
                //ensure that there are no duplicities in the answers


                //System.out.println("nb: " + String.valueOf(nb1 * nb2));
            //}
            repetition = true;
            i++;
        }
        arrList.add(corAnswerPos, arrList.get(0));
        arrList.remove(0);

       task = new MathRandomSix(
                arrList.get(0),
                arrList.get(1),
                arrList.get(2),
                arrList.get(3),
                arrList.get(4),
                arrList.get(5),
                res,
                corAnswerPos);
        System.out.println("task: " + String.valueOf("CorrAnswer1: " + corAnswerPos));
    }

    private int generateRandomInt(){
        return (int) (Math.random() * MAX);
    }

    private void assignToLayout(){
        Log.d(TAG, "assignToLayout");
        cd1.setText(String.valueOf(task.getNumber1()));
        cd2.setText(String.valueOf(task.getNumber2()));
        cd3.setText(String.valueOf(task.getNumber3()));
        cd4.setText(String.valueOf(task.getNumber4()));
        cd5.setText(String.valueOf(task.getNumber5()));
        cd6.setText(String.valueOf(task.getNumber6()));
        result.setText(a + " * " + b + " = ?");
        arrList.clear(); //   clear array list

        if (questionCounter != questionsAmount){
            nextButton.setText("ODPOVĚZ");
            nextButton.setVisibility(View.INVISIBLE);
            Toast.makeText(MathMultiplication.this,"Vyber správnou odpověď", Toast.LENGTH_SHORT).show();

            textViewScore.setText("Score: "+String.valueOf(score));
            textViewQuestionCount.setText("Questions: " + String.valueOf(questionCnt) + "/"+ String.valueOf(questionsAmount));
            //setEnabled(false);
        }

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
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
                showSolution();
                nextButton.setText("DALŠi");
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),   "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCD);
        }

    }

    
    
   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }*/

    /*private void finishMultiplacation() {
        Intent intent = new Intent();
        startActivity(intent);
    }*/

}