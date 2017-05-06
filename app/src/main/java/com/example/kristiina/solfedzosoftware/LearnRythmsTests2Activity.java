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

public class LearnRythmsTests2Activity extends AppCompatActivity {
    public static final String PREFERENCES = "Preferences";

    private TextView question;
    private TextView nextBtn;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioGroup radioGroup;

    String questions [] = { "NOODIJOONESTIK KOOSNEB ..... JOONEST.", "............ NÄITAB, MITU LÖÖKI ON ÜHES TAKTIS."};
    String options [][] = {{"4", "5","6"},{"TAKTIMÕÕT", "PAUS", "VIIULIVÕTI"}};
    String answer [] = {"5", "TAKTIMÕÕT"};

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
                    question.setText(questions[questionNumber]);
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
        Runtime.getRuntime().gc();
        finish();
    }

}
