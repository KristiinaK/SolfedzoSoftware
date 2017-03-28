package com.example.kristiina.solfedzosoftware;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LearnRythmsByPicture extends AppCompatActivity {

    TextView nextButton;
    TextView answer_textview;
    ImageView imageView;

    int right_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_by_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nextButton = (TextView) findViewById(R.id.nextbuttonRythmView);
        answer_textview = (TextView)findViewById(R.id.answer_textview_rythms);
        imageView = (ImageView) findViewById(R.id.rythmImage);

        right_answer = 2;

    }


    public void onClick_next_Rythms(final View view) {
        Random random = new Random();
        right_answer = random.nextInt(10) + 1;
        nextButton.setEnabled(false);
        answer_textview.setText("");

        switch (right_answer) {
            case 1:
                imageView.setImageResource(R.drawable.ta);
                break;
            case 2:
                imageView.setImageResource(R.drawable.titi);
                break;
            case 3:
                imageView.setImageResource(R.drawable.tiri_tiri);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ti_tiri);
                break;
            case 5:
                imageView.setImageResource(R.drawable.tiri_ti);
                break;
            case 6:
                imageView.setImageResource(R.drawable.tai_ri);
                break;
            case 7:
                imageView.setImageResource(R.drawable.taa);
                break;
            case 8:
                imageView.setImageResource(R.drawable.taaa);
                break;
            case 9:
                imageView.setImageResource(R.drawable.taaaa);
                break;
            case 10:
                imageView.setImageResource(R.drawable.ta_i_ti);
                break;
        }
    }

    public void onClick_TA(View v){
        if(right_answer==1){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }

    public void onClick_TI_TI(View v){
        if(right_answer==2) {
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TI-TI!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TIRI_TIRI(View v){
        if(right_answer==3) {
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TIRI-TIRI!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TI_TIRI(View v){
        if(right_answer==4) {
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TI-TIRI!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TIRI_TI(View v){
        if(right_answer==5){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TIRI-TI!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TAI_RI(View v){
        if(right_answer==6){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TAI-RI!");
            nextButton.setEnabled(true);
        } else{
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_A(View v){
        if(right_answer==7){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-A!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_A_A(View v){
        if(right_answer==8) {
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-A-A!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_A_A_A(View v){
        if(right_answer==9){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-A-A-A!");
            nextButton.setEnabled(true);
        } else{
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_I_TI(View v){
        if(right_answer==10){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-I-TI!");
            nextButton.setEnabled(true);
        } else {
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
}
