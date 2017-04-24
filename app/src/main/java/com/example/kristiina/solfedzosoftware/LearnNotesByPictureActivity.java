package com.example.kristiina.solfedzosoftware;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Random;

public class LearnNotesByPictureActivity extends AppCompatActivity {

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

    int right_answer;
    TextView nextButton;
    TextView aswer_textview;
    LinearLayout upper;
    LinearLayout bottom;
    HorizontalScrollView myScroll;
    private boolean right_ans_clicked=false;
    Button right_btn_id;
    Button clickedButton;

    int button_voice;

    int note_C3;
    int note_Cis3;
    int note_D3;
    int note_Dis3;
    int note_E3;
    int note_F3;
    int note_Fis3;
    int note_G3;
    int note_Gis3;
    int note_A3;
    int note_Ais3;
    int note_H3;

    int note_C4;
    int note_Cis4;
    int note_D4;
    int note_Dis4;
    int note_E4;
    int note_F4;
    int note_Fis4;
    int note_G4;
    int note_Gis4;
    int note_A4;
    int note_Ais4;
    int note_H4;

    int note_C5;
    int note_Cis5;
    int note_D5;
    int note_Dis5;
    int note_E5;
    int note_F5;
    int note_Fis5;
    int note_G5;
    int note_Gis5;
    int note_A5;
    int note_Ais5;
    int note_H5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_by_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadNotes();
        init();

        right_answer=4;
    }

    private void init(){
        nextButton= (TextView) findViewById(R.id.nextbutton);
        aswer_textview= (TextView) findViewById(R.id.answer_textview);
        upper= (LinearLayout) findViewById(R.id.upper_linearlayout);
        bottom= (LinearLayout) findViewById(R.id.bottom_linearlayout);
        myScroll = (HorizontalScrollView) findViewById(R.id.horizontal_scrollview2);

        nextButton.setEnabled(false);

        myScroll.post(new Runnable() {

            @Override
            public void run() {
                myScroll.scrollTo((myScroll.getChildAt(0).getLeft()+myScroll.getChildAt(0).getRight()-myScroll.getWidth())/2,0);
            }
        });
    }


    protected void createSoundPool(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            attributeBuilder= new AudioAttributes.Builder();
            attributeBuilder.setUsage(AudioAttributes.USAGE_GAME);
            attributeBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            attributes=attributeBuilder.build();

            soundPoolBuilder = new SoundPool.Builder();
            soundPoolBuilder.setAudioAttributes(attributes);
            soundPool=soundPoolBuilder.build();
        }else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
    }

    protected void loadNotes(){
        button_voice = soundPool.load(this, R.raw.rythm_button,1);

        note_C3 = soundPool.load(this, R.raw.c_3,1);
        note_Cis3 = soundPool.load(this, R.raw.cis_3,1);
        note_D3 = soundPool.load(this, R.raw.d_3,1);
        note_Dis3 = soundPool.load(this, R.raw.dis_3,1);
        note_E3 = soundPool.load(this, R.raw.e_3,1);
        note_F3 = soundPool.load(this, R.raw.f_3,1);
        note_Fis3 = soundPool.load(this, R.raw.fis_3,1);
        note_G3 = soundPool.load(this, R.raw.g_3,1);
        note_Gis3 = soundPool.load(this, R.raw.gis_3,1);
        note_A3 = soundPool.load(this, R.raw.a_3,1);
        note_Ais3 = soundPool.load(this, R.raw.ais_3,1);
        note_H3 = soundPool.load(this, R.raw.h_3,1);

        note_C4 = soundPool.load(this, R.raw.c_4,1);
        note_Cis4 = soundPool.load(this, R.raw.cis_4,1);
        note_D4 = soundPool.load(this, R.raw.d_4,1);
        note_Dis4 = soundPool.load(this, R.raw.dis_4,1);
        note_E4 = soundPool.load(this, R.raw.e_4,1);
        note_F4 = soundPool.load(this, R.raw.f_4,1);
        note_Fis4 = soundPool.load(this, R.raw.fis_4,1);
        note_G4 = soundPool.load(this, R.raw.g_4,1);
        note_Gis4 = soundPool.load(this, R.raw.gis_4,1);
        note_A4 = soundPool.load(this, R.raw.a_4,1);
        note_Ais4 = soundPool.load(this, R.raw.ais_4,1);
        note_H4 = soundPool.load(this, R.raw.h_4,1);

        note_C5 = soundPool.load(this, R.raw.c_5,1);
        note_Cis5 = soundPool.load(this, R.raw.cis_5,1);
        note_D5 = soundPool.load(this, R.raw.d_5,1);
        note_Dis5 = soundPool.load(this, R.raw.dis_5,1);
        note_E5 = soundPool.load(this, R.raw.e_5,1);
        note_F5 = soundPool.load(this, R.raw.f_5,1);
        note_Fis5 = soundPool.load(this, R.raw.fis_5,1);
        note_G5 = soundPool.load(this, R.raw.g_5,1);
        note_Gis5 = soundPool.load(this, R.raw.gis_5,1);
        note_A5 = soundPool.load(this, R.raw.a_5,1);
        note_Ais5 = soundPool.load(this, R.raw.ais_5,1);
        note_H5 = soundPool.load(this, R.raw.h_5,1);
    }

    private void generateRandom(){
        Random random= new Random();
        right_answer=random.nextInt(7)+1;
    }

    public void onClick_next(final View view){
        soundPool.play(button_voice, 1, 1, 0, 0, 1);

        generateRandom();
        initNextButton();

        switch (right_answer){
            case 1:
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2f
                );
                upper.setLayoutParams(param);
                LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3f
                );
                bottom.setLayoutParams(param2);

                break;
            case 2:
                LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        5f
                );
                upper.setLayoutParams(param3);
                LinearLayout.LayoutParams param4 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        0f
                );
                bottom.setLayoutParams(param4);
                break;
            case 3:
                LinearLayout.LayoutParams param5 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        4.5f
                );
                upper.setLayoutParams(param5);
                LinearLayout.LayoutParams param6 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        0.5f
                );
                bottom.setLayoutParams(param6);
                break;
            case 4:
                LinearLayout.LayoutParams param7 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        4f
                );
                upper.setLayoutParams(param7);
                LinearLayout.LayoutParams param8 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        1f
                );
                bottom.setLayoutParams(param8);
                break;
            case 5:
                LinearLayout.LayoutParams param9 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3.5f
                );
                upper.setLayoutParams(param9);
                LinearLayout.LayoutParams param10 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        1.5f
                );
                bottom.setLayoutParams(param10);

                break;
            case 6 :
                LinearLayout.LayoutParams param11 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3f
                );
                upper.setLayoutParams(param11);
                LinearLayout.LayoutParams param12 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2f
                );
                bottom.setLayoutParams(param12);

                break;
            case 7 :
                LinearLayout.LayoutParams param13 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2.5f
                );
                upper.setLayoutParams(param13);
                LinearLayout.LayoutParams param14 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2.5f
                );
                bottom.setLayoutParams(param14);
                break;
        }
    }

    private void initNextButton(){

        nextButton.setEnabled(false);
        aswer_textview.setText("");
        right_ans_clicked=false;
        right_btn_id.setBackgroundResource(R.drawable.piano_white_key);
        aswer_textview.setBackgroundResource(0);
    }

    public void onClick_C_3(final View view) {
        soundPool.play(note_C3, 1, 1, 0, 0, 1);
    }
    public void onClick_Cis_3(final View view) {
        soundPool.play(note_Cis3, 1, 1, 0, 0, 1);
    }
    public void onClick_D_3(final View view) {
        soundPool.play(note_D3, 1, 1, 0, 0, 1);
    }
    public void onClick_Dis_3(final View view) {
        soundPool.play(note_Dis3, 1, 1, 0, 0, 1);
    }
    public void onClick_E_3(final View view) {
        soundPool.play(note_E3, 1, 1, 0, 0, 1);
    }
    public void onClick_F_3(final View view) {
        soundPool.play(note_F3, 1, 1, 0, 0, 1);
    }
    public void onClick_Fis_3(final View view) {
        soundPool.play(note_Fis3, 1, 1, 0, 0, 1);
    }
    public void onClick_G_3(final View view) {
        soundPool.play(note_G3, 1, 1, 0, 0, 1);
    }
    public void onClick_Gis_3(final View view) {
        soundPool.play(note_Gis3, 1, 1, 0, 0, 1);
    }
    public void onClick_A_3(final View view) {
        soundPool.play(note_A3, 1, 1, 0, 0, 1);
    }
    public void onClick_Ais_3(final View view) {
        soundPool.play(note_Ais3, 1, 1, 0, 0, 1);
    }
    public void onClick_H_3(final View view) {
        soundPool.play(note_H3, 1, 1, 0, 0, 1);
    }

    public void onClick_C_4(final View view) {
        soundPool.play(note_C4, 1, 1, 0, 0, 1);
    }

    public void onClick_Cis_4(final View view) {
        soundPool.play(note_Cis4, 1, 1, 0, 0, 1);
    }



    public void onClick_D_4(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_D4);
        soundPool.play(note_D4, 1, 1, 0, 0, 1);

        if (right_answer == 2 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Dis_4(final View view) {soundPool.play(note_Dis4, 1, 1, 0, 0, 1);}


    public void onClick_E_4(final View view) {
        Button button = (Button)findViewById(R.id.btn_E4);
        soundPool.play(note_E4, 1, 1, 0, 0, 1);

        if (right_answer == 3 && !(right_ans_clicked)){
           rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }
    public void onClick_F_4(final View view) {
        Button button = (Button)findViewById(R.id.btn_F4);
        soundPool.play(note_F4, 1, 1, 0, 0, 1);

        if (right_answer == 4 && !(right_ans_clicked)){
           rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Fis_4(final View view) {
        soundPool.play(note_Fis4, 1, 1, 0, 0, 1);
    }

    public void onClick_G_4(final View view) {
        Button button = (Button)findViewById(R.id.btn_G4);
        soundPool.play(note_G4, 1, 1, 0, 0, 1);

        if (right_answer == 5 && !(right_ans_clicked)){
            rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_Gis_4(final View view) {
        soundPool.play(note_Gis4, 1, 1, 0, 0, 1);
    }

    public void onClick_A_4(final View view) {
        Button button = (Button)findViewById(R.id.btn_A4);
        soundPool.play(note_A4, 1, 1, 0, 0, 1);

        if (right_answer == 6 && !(right_ans_clicked)){
           rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Ais_4(final View view) {
        soundPool.play(note_Ais4, 1, 1, 0, 0, 1);
    }

    public void onClick_H_4(final View view) {
        Button button = (Button)findViewById(R.id.btn_H4);
        soundPool.play(note_H4, 1, 1, 0, 0, 1);

        if (right_answer == 7 && !(right_ans_clicked)){
           rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }



    public void onClick_C_5(final View view) {
        Button button = (Button)findViewById(R.id.btn_C5);
        soundPool.play(note_C5, 1, 1, 0, 0, 1);

        if (right_answer == 1 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
           falseAnswerClicked();
        }
    }
    public void onClick_Cis_5(final View view) {
        soundPool.play(note_Cis5, 1, 1, 0, 0, 1);
    }
    public void onClick_D_5(final View view) {
        soundPool.play(note_D5, 1, 1, 0, 0, 1);
    }
    public void onClick_Dis_5(final View view) {
        soundPool.play(note_Dis5, 1, 1, 0, 0, 1);
    }
    public void onClick_E_5(final View view) {
        soundPool.play(note_E5, 1, 1, 0, 0, 1);
    }
    public void onClick_F_5(final View view) {
        soundPool.play(note_F5, 1, 1, 0, 0, 1);
    }
    public void onClick_Fis_5(final View view) {
        soundPool.play(note_Fis5, 1, 1, 0, 0, 1);
    }
    public void onClick_G_5(final View view) {
        soundPool.play(note_G5, 1, 1, 0, 0, 1);
    }
    public void onClick_Gis_5(final View view) {
        soundPool.play(note_Gis5, 1, 1, 0, 0, 1);
    }
    public void onClick_A_5(final View view) {
        soundPool.play(note_A5, 1, 1, 0, 0, 1);
    }
    public void onClick_Ais_5(final View view) {
        soundPool.play(note_Ais5, 1, 1, 0, 0, 1);
    }
    public void onClick_H_5(final View view) {
        soundPool.play(note_H5, 1, 1, 0, 0, 1);
    }

    private void rightAnswerClicked(){
        nextButton.setEnabled(true);
        aswer_textview.setText("ÕIGE VASTUS "+ new String(Character.toChars(0x1F60A)));
        right_ans_clicked=true;
        right_btn_id=clickedButton;
        clickedButton.setBackgroundResource(R.drawable.piano_green_key);
        aswer_textview.setBackgroundResource(R.color.green);
    }

    private void falseAnswerClicked(){
        aswer_textview.setText("PROOVI VEEL");
        aswer_textview.setBackgroundResource(R.color.pink);
    }
    @Override
    protected void onPause(){
        super.onPause();
        soundPool.release();

    }
    @Override
    protected void onResume(){
        super.onResume();
        createSoundPool();
        loadNotes();
    }

}
