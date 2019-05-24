package com.example.acer.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private Button mT;
    private  Button mF;
    private Button mP;
    private  Button mN;
    private  Button cheatButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;


    private boolean cheat;


//questions

    private Questions[] mQuestionBank= new Questions[]{new  Questions(R.string.question_africa,true),
            new  Questions(R.string.question_africa,false),
            new  Questions(R.string.question_americas,true),
            new  Questions(R.string.question_mideast,false),
            new  Questions(R.string.question_oceans,false),
            new  Questions(R.string.question_asia,true)};




    private void updateQns(){

        int qn= mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(qn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            cheat = Cheat_Activity.wasAnswerShown(data);
        }
    }


    //checking

    private void checkQns(boolean userpressed){

       boolean ans= mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(cheat){

            messageResId=R.string.cheat;
        }else{
            if(userpressed==ans){

                messageResId=R.string.correct;
            }else {

                messageResId=R.string.incorrect;
            }



        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mF = (Button) findViewById(R.id.false_button);
        mN = (Button) findViewById(R.id.next_button);
        mP = (Button) findViewById(R.id.previous_button);
        mT=  (Button) findViewById(R.id.true_button);

        cheatButton = (Button)findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ans= mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i= Cheat_Activity.newIntent(QuizActivity.this,ans);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });


        mQuestionTextView=(TextView)findViewById(R.id.question_view);
        mQuestionTextView.setText(R.string.question_text);


        mT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQns(true);
            }
        });

        mF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkQns(false);
            }
        });

        mN=(Button)findViewById(R.id.next_button);
        mN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mCurrentIndex=(mCurrentIndex+1) % mQuestionBank.length;

                cheat=false;
                updateQns();
            }
        });

        mP=(Button)findViewById(R.id.previous_button);
        mP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mCurrentIndex=(mCurrentIndex-1) % mQuestionBank.length;

                updateQns();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQns();



    }





}
