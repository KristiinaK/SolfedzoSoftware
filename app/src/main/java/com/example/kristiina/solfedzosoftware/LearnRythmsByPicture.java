package com.example.kristiina.solfedzosoftware;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        nextButton.setEnabled(false);

        right_answer = 2;

    }


    public void onClick_next_Rythms(final View view) {


        right_ans_clicked=false;
        rightBtnId.setBackgroundResource(R.drawable.piano_white_key);
        answer_textview.setBackgroundResource(0);

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
    private boolean right_ans_clicked=false;
    Button clickedBtnId;
    Button rightBtnId;

    public void onClick_TA(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 1 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_TI_TI(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 2 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TIRI_TIRI(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 3 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TI_TIRI(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 4 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TIRI_TI(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 5 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TAI_RI(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 6 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TA_A(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 7 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TA_A_A(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 8 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TA_A_A_A(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 9 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_TA_I_TI(View v){
        clickedBtnId = (Button)findViewById(R.id.btn_ta);

        if (right_answer == 10 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    private void rightAnswerClicked(){
        nextButton.setEnabled(true);
        answer_textview.setText("ÕIGE VASTUS "+ new String(Character.toChars(0x1F60A)));
        right_ans_clicked=true;
        rightBtnId=clickedBtnId;
        clickedBtnId.setBackgroundResource(R.drawable.piano_green_key);
        answer_textview.setBackgroundResource(R.color.green);

    }
    private void falseAnswerClicked(){
        answer_textview.setText("PROOVI VEEL");
        answer_textview.setBackgroundResource(R.color.pink);
    }
}
