package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

//Quiz:
//https://www.youtube.com/watch?v=3sDo9afuPOI
//https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
//Shared preferences:
//https://developer.android.com/training/basics/data-storage/shared-preferences.html
public class LearnRythmsTests2Activity extends AppCompatActivity {
    public static final String PREFERENCES = "Preferences";

    //Quiz:
    //https://www.youtube.com/watch?v=3sDo9afuPOI
    //https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
    private TextView question;
    private TextView nextBtn;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioGroup radioGroup;
    String questions [] = { ".......... ON LÜHEMATE \n JA PIKEMATE HELIDE JÄRGNEVUS.","1", "RÜTMISILBID ON ........", "2", "NOODIPIKKUST EHK VÄLTUST \nMÄÄRAME ........", "3"};
    String options [][] = {{"RÜTM", "PAUS","TI-TI"},{"TI-TI", "TA", "TA-A"},{"TA, TI-TI, TA-A", "C, D, E", "DO, RE, MI"},{"TI-TI", "TIRI-TIRI", "TA-A"}, {"TAKTIDES", "PAUSIDES", "LÖÖKIDES"}, {"TAI-RI", "TA", "TA-A"}};
    String answer [] = {"RÜTM", "TA", "TA, TI-TI, TA-A", "TIRI-TIRI", "LÖÖKIDES", "TAI-RI"};
    private int questionNumber=0;
    public static int right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_tests2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Quiz:
        //https://www.youtube.com/watch?v=3sDo9afuPOI
        //https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
        question = (TextView) findViewById(R.id.questionR);
        nextBtn= (TextView) findViewById(R.id.nextBtnR);
        radioButton1= (RadioButton) findViewById(R.id.radioButtonR1);
        radioButton2= (RadioButton) findViewById(R.id.radioButtonR2);
        radioButton3= (RadioButton) findViewById(R.id.radioButtonR3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupR);
        question.setText(questions[questionNumber]);
        radioButton1.setText(options[questionNumber][0]);
        radioButton2.setText(options[questionNumber][1]);
        radioButton3.setText(options[questionNumber][2]);
        right=0;
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton clickedAnswer = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String answerText = clickedAnswer.getText().toString();
                if(answer[questionNumber].equals(answerText)){
                    right++;
                }
                questionNumber++;
                if(questionNumber>=questions.length){
                    //Shared preferences:
                    //https://developer.android.com/training/basics/data-storage/shared-preferences.html
                    SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);
                    SharedPreferences.Editor editor = preferences.edit();
                    int lastScore= preferences.getInt("scoreRythms",0);
                    int maximum= Math.max(lastScore,right);
                    editor.putInt("scoreRythms",maximum);
                    editor.commit();
                    Intent intent= new Intent(getApplicationContext(), LearnRythmsTests3Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    if (questions[questionNumber].equals("1")){
                        question.setText("MIS RÜTM ON PIDIL?");
                        question.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.ic_ta);
                    }else if(questions[questionNumber].equals("2")){
                        question.setText("MIS RÜTM ON PIDIL?");
                        question.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.ic_tiri_tiri);
                    }else if(questions[questionNumber].equals("3")){
                        question.setText("MIS RÜTM ON PIDIL?");
                        question.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.ic_tai_ri);
                    }
                    else{
                        question.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                        question.setText(questions[questionNumber]);
                    }
                    radioButton1.setText(options[questionNumber][0]);
                    radioButton2.setText(options[questionNumber][1]);
                    radioButton3.setText(options[questionNumber][2]);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        finish();
    }

}
