package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LearnNotesTests2Activity extends AppCompatActivity {


    TextView question;
    TextView nextBtn;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioGroup radioGroup;

    String questions [] = { "Mis on Krissu lemmikspordiala?", "Mis on Krissu lemmikvärv?"};
    String options [][] = {{"ujumine", "ratas","jooks"},{"sinine", "punane", "kollane"}};
    String answer [] = {"ujumine", "sinine"};

    int questionNumber=0;
    public static int right;
    public static int wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_tests2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        question = (TextView) findViewById(R.id.questionN);
        nextBtn= (TextView) findViewById(R.id.nextBtnN);
        radioButton1= (RadioButton) findViewById(R.id.radioButton1);
        radioButton2= (RadioButton) findViewById(R.id.radioButton2);
        radioButton3= (RadioButton) findViewById(R.id.radioButton3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupN);

        question.setText(questions[questionNumber]);
        radioButton1.setText(options[questionNumber][0]);
        radioButton2.setText(options[questionNumber][1]);
        radioButton3.setText(options[questionNumber][2]);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton clickedAnswer = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String answerText = clickedAnswer.getText().toString();

                if(answer[questionNumber].equals(answerText)){
                    right++;
                }else{
                    wrong++;
                }
                questionNumber++;

                if(questionNumber>=questions.length){
                    Intent intent= new Intent(getApplicationContext(), LearnNotesTests3Activity.class);
                    startActivity(intent);
                }else{
                    question.setText(questions[questionNumber]);
                    radioButton1.setText(options[questionNumber][0]);
                    radioButton2.setText(options[questionNumber][1]);
                    radioButton3.setText(options[questionNumber][2]);
                }
            }
        });


    }

}
