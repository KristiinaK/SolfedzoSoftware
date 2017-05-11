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
public class LearnNotesByListeningActivity extends AppCompatActivity {

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;
    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

    TextView answer_textview;
    TextView nextbutton;
    LinearLayout playbutton;
    HorizontalScrollView myScrollView ;

    int right_aswer;
    boolean playbuttonpressed;
    private boolean right_ans_clicked=false;
    Button right_btn_id;
    Button userClickedButton;
    int metronom;


    public static final String PREFERENCES = "Preferences";

    private String settings;

    int note_C3, note_Cis3, note_D3, note_Dis3, note_E3, note_F3, note_Fis3, note_G3, note_Gis3, note_A3, note_Ais3, note_H3;

    int note_C4, note_Cis4, note_D4, note_Dis4, note_E4, note_F4, note_Fis4, note_G4, note_Gis4, note_A4, note_Ais4, note_H4;

    int note_C5, note_Cis5, note_D5, note_Dis5, note_E5, note_F5, note_Fis5, note_G5, note_Gis5, note_A5, note_Ais5, note_H5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_by_listening);
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

        right_aswer=1;

    }

    private void init(){
        answer_textview = (TextView) findViewById(R.id.answerTextView);
        playbutton = (LinearLayout) findViewById(R.id.playButton);
        nextbutton= (TextView) findViewById(R.id.ButtonNext);
        myScrollView = (HorizontalScrollView) findViewById(R.id.horizontal_scrollview2);

        playbutton.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.GONE);

        //Shared preferences:
        //https://developer.android.com/training/basics/data-storage/shared-preferences.html
        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);
        settings= preferences.getString("settingsNotes","");

        if (settings.equals("ESIMENE OKTAV")) {
            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo((myScrollView.getChildAt(0).getLeft()+myScrollView.getChildAt(0).getRight()-myScrollView.getWidth())/2,0);
                }
            });
        }else if(settings.equals("TEINE OKTAV")){
            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo(myScrollView.getChildAt(0).getRight(),0);
                }
            });
        } else if (settings.equals("VÄIKE OKTAV")) {
            myScrollView.post(new Runnable() {

                @Override
                public void run() {

                    myScrollView.scrollTo(myScrollView.getChildAt(0).getLeft(),0);
                }
            });
        }else{
            settings="ESIMENE OKTAV" ;

            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo((myScrollView.getChildAt(0).getLeft()+myScrollView.getChildAt(0).getRight()-myScrollView.getWidth())/2,0);
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


    public void onClick_btn_play(final View view){
        playbuttonpressed=true;

        if (settings.equals("ESIMENE OKTAV")) {
            switch (right_aswer){
                case 1: soundPool.play(note_C4, 1, 1, 0, 0, 1);
                    break;
                case 2: soundPool.play(note_D4, 1, 1, 0, 0, 1);
                    break;
                case 3: soundPool.play(note_E4, 1, 1, 0, 0, 1);
                    break;
                case 4: soundPool.play(note_F4, 1, 1, 0, 0, 1);
                    break;
                case 5: soundPool.play(note_G4, 1, 1, 0, 0, 1);
                    break;
                case 6 : soundPool.play(note_A4, 1, 1, 0, 0, 1);
                    break;
                case 7 : soundPool.play(note_H4, 1, 1, 0, 0, 1);
                    break;
                default: soundPool.play(note_C4, 1, 1, 0, 0, 1);
                    break;
            }
        }else if(settings.equals("TEINE OKTAV")){
            switch (right_aswer){
                case 1: soundPool.play(note_C5, 1, 1, 0, 0, 1);
                    break;
                case 2: soundPool.play(note_D5, 1, 1, 0, 0, 1);
                    break;
                case 3: soundPool.play(note_E5, 1, 1, 0, 0, 1);
                    break;
                case 4: soundPool.play(note_F5, 1, 1, 0, 0, 1);
                    break;
                case 5: soundPool.play(note_G5, 1, 1, 0, 0, 1);
                    break;
                case 6 : soundPool.play(note_A5, 1, 1, 0, 0, 1);
                    break;
                case 7 : soundPool.play(note_H5, 1, 1, 0, 0, 1);
                    break;
                default: soundPool.play(note_C5, 1, 1, 0, 0, 1);
                    break;
            }
        } else if (settings.equals("VÄIKE OKTAV")) {
            switch (right_aswer){
                case 1: soundPool.play(note_C3, 1, 1, 0, 0, 1);
                    break;
                case 2: soundPool.play(note_D3, 1, 1, 0, 0, 1);
                    break;
                case 3: soundPool.play(note_E3, 1, 1, 0, 0, 1);
                    break;
                case 4: soundPool.play(note_F3, 1, 1, 0, 0, 1);
                    break;
                case 5: soundPool.play(note_G3, 1, 1, 0, 0, 1);
                    break;
                case 6 : soundPool.play(note_A3, 1, 1, 0, 0, 1);
                    break;
                case 7 : soundPool.play(note_H3, 1, 1, 0, 0, 1);
                    break;
                default: soundPool.play(note_C3, 1, 1, 0, 0, 1);
                    break;
            }
        }else{
            settings="ESIMENE OKTAV" ;
            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo((myScrollView.getChildAt(0).getLeft()+myScrollView.getChildAt(0).getRight()-myScrollView.getWidth())/2,0);
                }
            });           switch (right_aswer){
                case 1: soundPool.play(note_C4, 1, 1, 0, 0, 1);
                    break;
                case 2: soundPool.play(note_D4, 1, 1, 0, 0, 1);
                    break;
                case 3: soundPool.play(note_E4, 1, 1, 0, 0, 1);
                    break;
                case 4: soundPool.play(note_F4, 1, 1, 0, 0, 1);
                    break;
                case 5: soundPool.play(note_G4, 1, 1, 0, 0, 1);
                    break;
                case 6 : soundPool.play(note_A4, 1, 1, 0, 0, 1);
                    break;
                case 7 : soundPool.play(note_H4, 1, 1, 0, 0, 1);
                    break;
                default: soundPool.play(note_C4, 1, 1, 0, 0, 1);
                    break;
            }
        }

    }


    public  void onClickButtonNext(final View view){

        generateRandom();
        initNextButton();
    }

    private void initNextButton(){
        right_ans_clicked=false;
        right_btn_id.setBackgroundResource(R.drawable.piano_white_key);
        answer_textview.setBackgroundResource(0);
        playbuttonpressed=false;
        answer_textview.setText("");
        playbutton.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
    }

    private void generateRandom(){
        Random random= new Random();
        right_aswer=random.nextInt(7)+1;
    }

    public void onClick_C_3(final View view) {
        soundPool.play(note_C3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_C3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==1 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Cis_3(final View view) {
        soundPool.play(note_Cis3, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_D_3(final View view) {
        soundPool.play(note_D3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_D3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==2 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Dis_3(final View view) {
        soundPool.play(note_Dis3, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_E_3(final View view) {
        soundPool.play(note_E3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.brn_E3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==3 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_F_3(final View view) {
        soundPool.play(note_F3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_F3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==4 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Fis_3(final View view) {
        soundPool.play(note_Fis3, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_G_3(final View view) {
        soundPool.play(note_G3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_G3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==5 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Gis_3(final View view) {
        soundPool.play(note_Gis3, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_A_3(final View view) {
        soundPool.play(note_A3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_A3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==6 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Ais_3(final View view) {
        soundPool.play(note_Ais3, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_H_3(final View view) {
        soundPool.play(note_H3, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_H3);

        if(settings.equals("VÄIKE OKTAV")&&right_aswer==7 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }


    public void onClick_C_4(final View view) {
        userClickedButton = (Button)findViewById(R.id.btn_C4);
        soundPool.play(note_C4, 1, 1, 0, 0, 1);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==1 && playbuttonpressed && !(right_ans_clicked)){
           rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
           falseAnswer();
        }
    }

    public void onClick_Cis_4(final View view) {
        soundPool.play(note_Cis4, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_D_4(final View view) {
        userClickedButton = (Button)findViewById(R.id.btn_D4);
        soundPool.play(note_D4, 1, 1, 0, 0, 1);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==2 && playbuttonpressed && !(right_ans_clicked)){
           rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_Dis_4(final View view) {
        soundPool.play(note_Dis4, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_E_4(final View view) {
        soundPool.play(note_E4, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_E4);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==3 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_F_4(final View view) {
        userClickedButton= (Button)findViewById(R.id.btn_F4);
        soundPool.play(note_F4, 1, 1, 0, 0, 1);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==4 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_Fis_4(final View view) {
        soundPool.play(note_Fis4, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_G_4(final View view) {
        userClickedButton = (Button)findViewById(R.id.btn_G4);
        soundPool.play(note_G4, 1, 1, 0, 0, 1);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==5 && playbuttonpressed && !(right_ans_clicked)){
           rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Gis_4(final View view) {
        soundPool.play(note_Gis4, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_A_4(final View view) {
        userClickedButton = (Button)findViewById(R.id.btn_A4);
        soundPool.play(note_A4, 1, 1, 0, 0, 1);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==6 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();

        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_Ais_4(final View view) {
        soundPool.play(note_Ais4, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_H_4(final View view) {
        userClickedButton = (Button)findViewById(R.id.btn_H4);
        soundPool.play(note_H4, 1, 1, 0, 0, 1);

        if(settings.equals("ESIMENE OKTAV")&&right_aswer==7 && playbuttonpressed && !(right_ans_clicked)){
           rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_C_5(final View view) {
        soundPool.play(note_C5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_C5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==1 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Cis_5(final View view) {
        soundPool.play(note_Cis5, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_D_5(final View view) {
        soundPool.play(note_D5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_D5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==2 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Dis_5(final View view) {
        soundPool.play(note_Dis5, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_E_5(final View view) {
        soundPool.play(note_E5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.brn_E5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==3 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_F_5(final View view) {
        soundPool.play(note_F5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_F5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==4 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Fis_5(final View view) {
        soundPool.play(note_Fis5, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_G_5(final View view) {
        soundPool.play(note_G5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_G5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==5 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Gis_5(final View view) {
        soundPool.play(note_Gis5, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_A_5(final View view) {
        soundPool.play(note_A5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_A5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==6 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_Ais_5(final View view) {
        soundPool.play(note_Ais5, 1, 1, 0, 0, 1);
        if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_H_5(final View view) {
        soundPool.play(note_H5, 1, 1, 0, 0, 1);
        userClickedButton = (Button)findViewById(R.id.btn_H5);

        if(settings.equals("TEINE OKTAV")&&right_aswer==7 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    private void rightAnswer(){
        right_ans_clicked=true;
        //http://stackoverflow.com/a/26894146
        answer_textview.setText("ÕIGE VASTUS "+ new String(Character.toChars(0x1F60A)));

        playbutton.setVisibility(View.GONE);
        nextbutton.setVisibility(View.VISIBLE);
        userClickedButton.setBackgroundResource(R.drawable.piano_green_key);
        answer_textview.setBackgroundResource(R.color.green);
        right_btn_id=userClickedButton;
    }

    private void falseAnswer(){
        answer_textview.setText("PROOVI VEEL");
        answer_textview.setBackgroundResource(R.color.pink);
    }

    //https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.settings).setVisible(false);
        menu.findItem(R.id.settingsKey).setVisible(false);
        return true;
    }
    //https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settingsNotes) {
            Intent intent=new Intent(this,SettingsNotesByListening.class);
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
    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
    @Override
    protected void onResume(){
        super.onResume();
        createSoundPool();
        loadNotes();
        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);


        settings= preferences.getString("settingsNotes","");

        if (settings.equals("ESIMENE OKTAV")) {
            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo((myScrollView.getChildAt(0).getLeft()+myScrollView.getChildAt(0).getRight()-myScrollView.getWidth())/2,0);
                }
            });
        }else if(settings.equals("TEINE OKTAV")){
            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo(myScrollView.getChildAt(0).getRight(),0);
                }
            });
        } else if (settings.equals("VÄIKE OKTAV")) {
            myScrollView.post(new Runnable() {

                @Override
                public void run() {

                    myScrollView.scrollTo(myScrollView.getChildAt(0).getLeft(),0);
                }
            });
        }else{
            myScrollView.post(new Runnable() {

                @Override
                public void run() {
                    myScrollView.scrollTo((myScrollView.getChildAt(0).getLeft()+myScrollView.getChildAt(0).getRight()-myScrollView.getWidth())/2,0);
                }
            });
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }


}
