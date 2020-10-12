package com.gloxeer.pocketclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MathMultiplication extends AppCompatActivity {

    //declare variable
    private static final String TAG = null;
    private TextView cd1, cd2, cd3, cd4, cd5, cd6, result;
    int nb1, nb2, nb3, nb4, nb5, nb6, res, corAnswerPos, a, b;
    private Button nextButton;
    private ArrayList<Integer> arrList = new ArrayList<>();
    private static int MAX = 15;
    MathRandomSix task;
    int clickCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_multiplication);



        nextButton = findViewById(R.id.math_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "nextButton.setOnClickListener");
                getQuestion();
                assignToLayout();
                if (clickCount == 6){
                    clickCount = 0;
                    finish();
                } else {
                    clickCount++;
                }
            }
        });
    }

    private void getQuestion(){
        Log.d(TAG, "getQuestion");
        a = (int) (Math.random() * MAX);
        b = (int) (Math.random() * MAX);
        res = a * b;

        corAnswerPos = (int) (Math.random() * 6);
        //corAnswerPos = 2;
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
    }

    private void assignToLayout(){
        Log.d(TAG, "assignToLayout");
        cd1 = findViewById(R.id.text_math_cd1);
        cd1.setText(String.valueOf(task.getNumber1()));
        cd2 = findViewById(R.id.text_math_cd2);
        cd2.setText(String.valueOf(task.getNumber2()));
        cd3 = findViewById(R.id.text_math_cd3);
        cd3.setText(String.valueOf(task.getNumber3()));
        cd4 = findViewById(R.id.text_math_cd4);
        cd4.setText(String.valueOf(task.getNumber4()));
        cd5 = findViewById(R.id.text_math_cd5);
        cd5.setText(String.valueOf(task.getNumber5()));
        cd6 = findViewById(R.id.text_math_cd6);
        cd6.setText(String.valueOf(task.getNumber6()));
        result = findViewById(R.id.text_math_result);
        result.setText(a + " * " + b + " = " + String.valueOf(task.getResult()));
    }

    private void finishTest(){

    }

}