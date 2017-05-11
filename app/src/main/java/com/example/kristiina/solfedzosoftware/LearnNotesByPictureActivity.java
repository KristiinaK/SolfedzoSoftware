package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Random;

//Soundpool:
//https://www.youtube.com/watch?v=wQ2DKxNtrT4
//https://www.youtube.com/watch?v=byNOLwmzNz0
//https://www.youtube.com/watch?v=bslTj2zDARc
//Shared preferences:
//https://developer.android.com/training/basics/data-storage/shared-preferences.html
//Menus:
//https://developer.android.com/guide/topics/ui/menus.html
public class LearnNotesByPictureActivity extends AppCompatActivity {

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
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
    ImageView pianokey;
    View upperNoteLine;
    View bottomNoteLine;
    int metronom;


    public static final String PREFERENCES = "Preferences";
    private String settings;


    int note_C3, note_Cis3, note_D3, note_Dis3, note_E3, note_F3, note_Fis3, note_G3, note_Gis3, note_A3, note_Ais3, note_H3;

    int note_C4, note_Cis4, note_D4, note_Dis4, note_E4, note_F4, note_Fis4, note_G4, note_Gis4, note_A4, note_Ais4, note_H4;

    int note_C5, note_Cis5, note_D5, note_Dis5, note_E5, note_F5, note_Fis5, note_G5, note_Gis5, note_A5, note_Ais5, note_H5;
    public static final Random RANDOM = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_by_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Soundpool:
        //https://www.youtube.com/watch?v=wQ2DKxNtrT4
        //https://www.youtube.com/watch?v=byNOLwmzNz0
        //https://www.youtube.com/watch?v=bslTj2zDARc
        createSoundPool();
        loadNotes();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        init();
        right_answer=6;

    }

    private void init(){
        nextButton= (TextView) findViewById(R.id.nextbutton);
        aswer_textview= (TextView) findViewById(R.id.answer_textview);
        upper= (LinearLayout) findViewById(R.id.upper_linearlayout);
        bottom= (LinearLayout) findViewById(R.id.bottom_linearlayout);
        myScroll = (HorizontalScrollView) findViewById(R.id.horizontal_scrollview2);
        pianokey = (ImageView) findViewById(R.id.voti);
        upperNoteLine = findViewById(R.id.upperNoteLine);
        bottomNoteLine = findViewById(R.id.bottomNoteLine);
        nextButton.setEnabled(false);

        myScroll.post(new Runnable() {

            @Override
            public void run() {
                myScroll.scrollTo((myScroll.getChildAt(0).getLeft()+myScroll.getChildAt(0).getRight()-myScroll.getWidth())/2,0);
            }
        });
        //Shared preferences:
        //https://developer.android.com/training/basics/data-storage/shared-preferences.html
        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);
        settings= preferences.getString("settingsNotes","");

        if (settings.equals("VIIULIVÕTI")) {
            myScroll.post(new Runnable() {

                @Override
                public void run() {
                    myScroll.scrollTo((myScroll.getChildAt(0).getLeft()+myScroll.getChildAt(0).getRight()-myScroll.getWidth())/2,0);
                }
            });
            pianokey.setImageResource(R.drawable.viiulivoti);
        }else if(settings.equals("BASSIVÕTI")){
            myScroll.post(new Runnable() {

                @Override
                public void run() {
                    myScroll.scrollTo(myScroll.getChildAt(0).getRight(),0);
                }
            });
            pianokey.setImageResource(R.drawable.bassivoti);

        }else{
            settings="VIIULIVÕTI" ;
            pianokey.setImageResource(R.drawable.viiulivoti);

            myScroll.post(new Runnable() {

                @Override
                public void run() {
                    myScroll.scrollTo((myScroll.getChildAt(0).getLeft()+myScroll.getChildAt(0).getRight()-myScroll.getWidth())/2,0);
                }
            });
        }

    }

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
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
    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
    protected void loadNotes(){
        // Sound downloaded from: https://www.soundjay.com/button-sounds-5.html
        metronom = soundPool.load(this, R.raw.rythm_button, 1);
        //https://www.freesound.org/people/jobro/packs/2489/?page=2#sound
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
        right_answer= RANDOM.nextInt(14)+1;
    }

    private void generateRandomBass(){
        right_answer=RANDOM.nextInt(7)+6;
    }

    public void onClick_next(final View view){


        initNextButton();

        if(settings.equals("VIIULIVÕTI")){
            generateRandom();

        }else if(settings.equals("BASSIVÕTI")){
            generateRandomBass();
        }else{
            settings="VIIULIVÕTI";
            generateRandom();

        }

        switch (right_answer){
            case 1:
                bottomNoteLine.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        6.5f
                );
                upper.setLayoutParams(param);
                LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        0.5f
                );
                bottom.setLayoutParams(param2);

                break;

            case 2:
                LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        6f
                );
                upper.setLayoutParams(param3);
                LinearLayout.LayoutParams param4 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        1f
                );
                bottom.setLayoutParams(param4);
                break;
            case 3:
                LinearLayout.LayoutParams param5 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        5.5f
                );
                upper.setLayoutParams(param5);
                LinearLayout.LayoutParams param6 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        1.5f
                );
                bottom.setLayoutParams(param6);
                break;
            case 4:
                LinearLayout.LayoutParams param7 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        5f
                );
                upper.setLayoutParams(param7);
                LinearLayout.LayoutParams param8 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2f
                );
                bottom.setLayoutParams(param8);
                break;
            case 5:
                LinearLayout.LayoutParams param9 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        4.5f
                );
                upper.setLayoutParams(param9);
                LinearLayout.LayoutParams param10 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2.5f
                );
                bottom.setLayoutParams(param10);

                break;
            case 6 :
                LinearLayout.LayoutParams param11 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        4f
                );
                upper.setLayoutParams(param11);
                LinearLayout.LayoutParams param12 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3f
                );
                bottom.setLayoutParams(param12);

                break;
            case 7 :
                LinearLayout.LayoutParams param13 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3.5f
                );
                upper.setLayoutParams(param13);
                LinearLayout.LayoutParams param14 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3.5f
                );
                bottom.setLayoutParams(param14);
                break;
            case 8 :
                LinearLayout.LayoutParams param15 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        3f
                );
                upper.setLayoutParams(param15);
                LinearLayout.LayoutParams param16 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        4f
                );
                bottom.setLayoutParams(param16);
                break;
            case 9 :
                LinearLayout.LayoutParams param17 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2.5f
                );
                upper.setLayoutParams(param17);
                LinearLayout.LayoutParams param18 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        4.5f
                );
                bottom.setLayoutParams(param18);
                break;
            case 10 :
                LinearLayout.LayoutParams param19 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        2f
                );
                upper.setLayoutParams(param19);
                LinearLayout.LayoutParams param20 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        5f
                );
                bottom.setLayoutParams(param20);
                break;
            case 11 :
                LinearLayout.LayoutParams param21 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        1.5f
                );
                upper.setLayoutParams(param21);
                LinearLayout.LayoutParams param22 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        5.5f
                );
                bottom.setLayoutParams(param22);
                break;
            case 12 :
                LinearLayout.LayoutParams param23 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        1f
                );
                upper.setLayoutParams(param23);
                LinearLayout.LayoutParams param24 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        6f
                );
                bottom.setLayoutParams(param24);
                break;
            case 13 :
                upperNoteLine.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams param25 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        0.5f
                );
                upper.setLayoutParams(param25);
                LinearLayout.LayoutParams param26 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        6.5f
                );
                bottom.setLayoutParams(param26);
                break;
            case 14 :
                upperNoteLine.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams param27 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        0f
                );
                upper.setLayoutParams(param27);
                LinearLayout.LayoutParams param28 = new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        0,
                        7f
                );
                bottom.setLayoutParams(param28);
                break;
        }
    }

    private void initNextButton(){

        nextButton.setEnabled(false);
        aswer_textview.setText("");
        right_ans_clicked=false;
        right_btn_id.setBackgroundResource(R.drawable.piano_white_key);
        aswer_textview.setBackgroundResource(0);
        bottomNoteLine.setVisibility(View.INVISIBLE);
        upperNoteLine.setVisibility(View.INVISIBLE);
    }

    public void onClick_C_3(final View view) {
        soundPool.play(note_C3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_C3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 6 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Cis_3(final View view) {
        soundPool.play(note_Cis3, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_D_3(final View view) {
        soundPool.play(note_D3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_D3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 7 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Dis_3(final View view) {
        soundPool.play(note_Dis3, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_E_3(final View view) {
        soundPool.play(note_E3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.brn_E3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 8 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_F_3(final View view) {
        soundPool.play(note_F3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_F3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 9 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Fis_3(final View view) {
        soundPool.play(note_Fis3, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_G_3(final View view) {
        soundPool.play(note_G3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_G3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 10 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Gis_3(final View view) {
        soundPool.play(note_Gis3, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_A_3(final View view) {
        soundPool.play(note_A3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_A3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 11 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Ais_3(final View view) {
        soundPool.play(note_Ais3, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_H_3(final View view) {
        soundPool.play(note_H3, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_H3);

        if (settings.equals("BASSIVÕTI")&&right_answer == 12 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_C_4(final View view) {
        soundPool.play(note_C4, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_C4);

        if (settings.equals("VIIULIVÕTI")&&right_answer == 1 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Cis_4(final View view) {
        soundPool.play(note_Cis4, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }



    public void onClick_D_4(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_D4);
        soundPool.play(note_D4, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&&right_answer == 2 && !(right_ans_clicked)){
            rightAnswerClicked();
        } else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Dis_4(final View view) {
        soundPool.play(note_Dis4, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }


    public void onClick_E_4(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_E4);
        soundPool.play(note_E4, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&&right_answer == 3 && !(right_ans_clicked)){
           rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }
    public void onClick_F_4(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_F4);
        soundPool.play(note_F4, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&&right_answer == 4 && !(right_ans_clicked)){
           rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Fis_4(final View view) {
        soundPool.play(note_Fis4, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_G_4(final View view) {
        clickedButton  = (Button)findViewById(R.id.btn_G4);
        soundPool.play(note_G4, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&&right_answer == 5 && !(right_ans_clicked)){
            rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_Gis_4(final View view) {
        soundPool.play(note_Gis4, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_A_4(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_A4);
        soundPool.play(note_A4, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&&right_answer == 6 && !(right_ans_clicked)){
           rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }

    public void onClick_Ais_4(final View view) {
        soundPool.play(note_Ais4, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    public void onClick_H_4(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_H4);
        soundPool.play(note_H4, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 7 && !(right_ans_clicked)){
           rightAnswerClicked();

        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }



    public void onClick_C_5(final View view) {
        clickedButton = (Button)findViewById(R.id.btn_C5);
        soundPool.play(note_C5, 1, 1, 0, 0, 1);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 8 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
           falseAnswerClicked();
        }
    }
    public void onClick_Cis_5(final View view) {
        soundPool.play(note_Cis5, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_D_5(final View view) {
        soundPool.play(note_D5, 1, 1, 0, 0, 1);

        clickedButton = (Button)findViewById(R.id.btn_D5);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 9 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Dis_5(final View view) {
        soundPool.play(note_Dis5, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_E_5(final View view) {
        soundPool.play(note_E5, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.brn_E5);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 10 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_F_5(final View view) {
        soundPool.play(note_F5, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_F5);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 11 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Fis_5(final View view) {
        soundPool.play(note_Fis5, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }

    }
    public void onClick_G_5(final View view) {
        soundPool.play(note_G5, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_G5);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 12 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Gis_5(final View view) {
        soundPool.play(note_Gis5, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_A_5(final View view) {
        soundPool.play(note_A5, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_A5);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 13 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_Ais_5(final View view) {
        soundPool.play(note_Ais5, 1, 1, 0, 0, 1);
        if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }
    public void onClick_H_5(final View view) {
        soundPool.play(note_H5, 1, 1, 0, 0, 1);
        clickedButton = (Button)findViewById(R.id.btn_H5);

        if (settings.equals("VIIULIVÕTI")&& right_answer == 14 && !(right_ans_clicked)){
            rightAnswerClicked();
        }else if(!(right_ans_clicked)){
            falseAnswerClicked();
        }
    }

    private void rightAnswerClicked(){
        nextButton.setEnabled(true);

        //http://stackoverflow.com/a/26894146
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

    //Menus:
    //https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.settings).setVisible(false);
        menu.findItem(R.id.settingsNotes).setVisible(false);

        return true;
    }

    //Menus:
    //https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Menus:
    //https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settingsKey) {
            Intent intent=new Intent(this,SettingsNotesByPicture.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
    @Override
    protected void onPause(){
        super.onPause();
        soundPool.release();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();

    }

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
    //Shared preferences:
    //https://developer.android.com/training/basics/data-storage/shared-preferences.html
    @Override
    protected void onResume(){

        super.onResume();
        createSoundPool();
        loadNotes();
        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);


        settings= preferences.getString("settingsNotesByPicture","");

        if (settings.equals("VIIULIVÕTI")) {

            myScroll.post(new Runnable() {

                @Override
                public void run() {
                    myScroll.scrollTo((myScroll.getChildAt(0).getLeft()+myScroll.getChildAt(0).getRight()-myScroll.getWidth())/2,0);
                }
            });
            pianokey.setImageResource(R.drawable.viiulivoti);
        }else if(settings.equals("BASSIVÕTI")){
            myScroll.post(new Runnable() {

                @Override
                public void run() {
                    myScroll.scrollTo(myScroll.getChildAt(0).getLeft(),0);
                }
            });
            pianokey.setImageResource(R.drawable.bassivoti);

        }else{
            settings="VIIULIVÕTI" ;
            pianokey.setImageResource(R.drawable.viiulivoti);

            myScroll.post(new Runnable() {

                @Override
                public void run() {
                    myScroll.scrollTo((myScroll.getChildAt(0).getLeft()+myScroll.getChildAt(0).getRight()-myScroll.getWidth())/2,0);
                }
            });
        }



    }

}
