package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MathMultiplication extends AppCompatActivity implements View.OnClickListener{

    //declare variable
    private static final String TAG = "MathMultiplicationActivity";
    private TextView cd1, cd2, cd3, cd4, cd5, cd6, result;
    private CardView ccd1, ccd2, ccd3, ccd4, ccd5, ccd6, cresult;
    int nb1, nb2, nb3, nb4, nb5, nb6, res, corAnswerPos, a, b;
    private Button nextButton;
    private ArrayList<Integer> arrList = new ArrayList<>();
    private static int MAX = 15;
    MathRandomSix task;
    int clickCount;
    int questionsAmount = 3;
    int questionCounter = 1;
    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_multiplication);

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

        nextButton = findViewById(R.id.math_button);
        //loads first numbers
        nextQuestion();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "nextButton.setOnClickListener");
                if (questionCounter == questionsAmount){
                    finish();
                } else {
                    questionCounter++;
                }
                if (answered) {
                    nextQuestion();
                    answered = false;
                }

            }
        });
    }

    // @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.math_cd1:
                ccd1 = (CardView) findViewById(R.id.math_cd1);
                ccd1.setCardBackgroundColor(Color.YELLOW);
                Toast.makeText(this, "Math", Toast.LENGTH_SHORT).show();
                break;
            case R.id.math_cd2:
                ccd2 = (CardView) findViewById(R.id.math_cd2);
                ccd2.setCardBackgroundColor(Color.YELLOW);
                Toast.makeText(this, "Math", Toast.LENGTH_SHORT).show();
                break;
            case R.id.math_cd3:
                ccd3.setCardBackgroundColor(Color.YELLOW);
                break;
            case R.id.math_cd4:
                ccd4.setCardBackgroundColor(Color.YELLOW);
                break;
            case R.id.math_cd5:
                ccd5.setCardBackgroundColor(Color.YELLOW);
                break;
            case R.id.math_cd6:
                ccd6.setCardBackgroundColor(Color.YELLOW);
                break;
        }
        answered = true;
    }

    private void nextQuestion(){
        getQuestion();
        assignToLayout();
        showSolution();
    }

    public void showSolution(){
        ccd1.setCardBackgroundColor(Color.WHITE);
        ccd2.setCardBackgroundColor(Color.WHITE);
        ccd3.setCardBackgroundColor(Color.WHITE);
        ccd4.setCardBackgroundColor(Color.WHITE);
        ccd5.setCardBackgroundColor(Color.WHITE);
        ccd6.setCardBackgroundColor(Color.WHITE);


        System.out.println("task: " + String.valueOf("CorrAnswer2: "+ corAnswerPos));
        switch (corAnswerPos+1  ){
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
        corAnswerPos = 0;

       if (answered = true){
            nextButton.setText("DALSÍ");
       } else {
           nextButton.setText("POTVRĎ");
       }
    }

    private void getQuestion(){
        Log.d(TAG, "getQuestion");
        a = (int) (Math.random() * MAX);
        b = (int) (Math.random() * MAX);
        res = a * b;

        corAnswerPos = (int) (Math.random() * 6);

        System.out.println(String.valueOf(corAnswerPos));
        int i = 0;
        while (i < 6){
            System.out.println("i: " + String.valueOf(i));
            if (i == corAnswerPos){
                arrList.add(res);
            } else {
                nb1 = (int) (Math.random() * MAX);
                nb2 = (int) (Math.random() * MAX);
                arrList.add(nb1 * nb2);
                System.out.println("nb: " + String.valueOf(nb1 * nb2));
            }
            i++;
        }

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

    private void assignToLayout(){
        Log.d(TAG, "assignToLayout");
        cd1.setText(String.valueOf(task.getNumber1()));
        cd2.setText(String.valueOf(task.getNumber2()));
        cd3.setText(String.valueOf(task.getNumber3()));
        cd4.setText(String.valueOf(task.getNumber4()));
        cd5.setText(String.valueOf(task.getNumber5()));
        cd6.setText(String.valueOf(task.getNumber6()));
        result.setText(a + " * " + b + " = " + String.valueOf(task.getResult()));
        arrList.clear(); //   clear array list
    }

    private void finishMultiplacation() {
        Intent intent = new Intent();
        startActivity(intent);
    }

}