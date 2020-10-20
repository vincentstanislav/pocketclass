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
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MathMultiplication extends AppCompatActivity implements View.OnClickListener{

    //declare variable
    private static final String TAG = "MMultiplicationActivity";
    private TextView cd1, cd2, cd3, cd4, cd5, cd6, result;
    private CardView ccd1, ccd2, ccd3, ccd4, ccd5, ccd6, cresult;
    int res, corAnswerPos, a, b;
    private Button nextButton;
    private ArrayList<Integer> arrList = new ArrayList<>();
    private static final int MAX = 15;
    private GridLayout gl;
    MathRandomSix task;
    int clickCount;
    int questionsAmount = 6;
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

       switch (v.getId()) {
            case R.id.math_cd1:
                ccd1.setCardBackgroundColor(Color.YELLOW);
                break;
            case R.id.math_cd2:
                ccd2.setCardBackgroundColor(Color.YELLOW);
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
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        nextButton.setText("POTVRĎ");
        answered = true;
        enableObjects(false);

        if (answered) {  //allow to select only one answer
            nextButton.setEnabled(true);
            //Toast.makeText(MathMultiplication.this,"Vyber správnou odpověď", Toast.LENGTH_SHORT).show();
        }
        System.out.println("geID_finish: " + String.valueOf(v.getId()));
        answered = true;
    }

    private void nextQuestion(){
        if (questionCounter == questionsAmount){
            finish();}
        else {
            setDefaultColors();
            enableObjects(true);
            getQuestion();
            assignToLayout();
        }
    }

    public void showSolution(){

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
        result.setText(a + " * " + b + " = " + String.valueOf(task.getResult()));
        corAnswerPos = 0;

        if (questionCounter == questionsAmount-1){
            nextButton.setText("UKONČIT");
        } else {
            nextButton.setText("DALŠÍ");
        }
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
        int numbersCount = 6;
        int i = 0;
        int nb1, nb2, resNumber = 0;
        boolean repetition = true;

        Log.d(TAG, "getQuestion");
        a = (int) (Math.random() * MAX);
        b = (int) (Math.random() * MAX);
        res = a * b;

        corAnswerPos = (int) (Math.random() * numbersCount);

        while (i < numbersCount){
           // System.out.println("i, res: " + String.valueOf(i) + "," + String.valueOf(res));
            if (i == corAnswerPos){
                arrList.add(res);
            } else {

                while (repetition = true) {
                    nb1 = generateRandomInt();
                    nb2 = generateRandomInt();
                    resNumber = nb1 * nb2;
                    //arrList.add(resNumber);

                    for (int j = 0; j < i+1; j++) {
                        if ((arrList.get(j) == resNumber) && (j>0)){
                           // System.out.println("j, newres: " + String.valueOf(j) + "," + String.valueOf(resNumber));
                            break;
                        }
                        if (j == i-1) repetition = false;
                    }

                }
                arrList.add(resNumber);
               // while (repetition = false) {
                //ensure that there are no duplicities in the answers


                //System.out.println("nb: " + String.valueOf(nb1 * nb2));
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
            nextButton.setEnabled(false);
        }
    }

    /*private void finishMultiplacation() {
        Intent intent = new Intent();
        startActivity(intent);
    }*/

}