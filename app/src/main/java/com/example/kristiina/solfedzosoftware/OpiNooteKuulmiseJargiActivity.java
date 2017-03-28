package com.example.kristiina.solfedzosoftware;


import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;


public class OpiNooteKuulmiseJargiActivity extends AppCompatActivity {

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

    TextView answer_textview;
    TextView nextbutton;
    LinearLayout playbutton;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opi_noote_kuulmise_jargi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        answer_textview = (TextView) findViewById(R.id.answerTextView);
        playbutton = (LinearLayout) findViewById(R.id.playButton);
        nextbutton= (TextView) findViewById(R.id.ButtonNext);

        //To connect mobile volume button with app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        playbutton.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.GONE);


        right_aswer=1;

        createSoundPool();
        loadNotes();
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


    int right_aswer;
    boolean playbuttonpressed;

    public void onClick_btn_play(final View view){

        playbuttonpressed=true;

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


    }

    public  void onClickButtonNext(final View view){
        playbuttonpressed=false;
        Random random= new Random();
        right_aswer=random.nextInt(7)+1;

        answer_textview.setText("");
        playbutton.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
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

        if(right_aswer==1 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON C!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }

    public void onClick_Cis_4(final View view) {
        soundPool.play(note_Cis4, 1, 1, 0, 0, 1);
    }

    public void onClick_D_4(final View view) {
        soundPool.play(note_D4, 1, 1, 0, 0, 1);
        if(right_aswer==2 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON D!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }

    public void onClick_Dis_4(final View view) {
        soundPool.play(note_Dis4, 1, 1, 0, 0, 1);
    }

    public void onClick_E_4(final View view) {
        soundPool.play(note_E4, 1, 1, 0, 0, 1);
        if(right_aswer==3 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON E!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_F_4(final View view) {
        soundPool.play(note_F4, 1, 1, 0, 0, 1);
        if(right_aswer==4 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON F!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }

    public void onClick_Fis_4(final View view) {
        soundPool.play(note_Fis4, 1, 1, 0, 0, 1);
    }

    public void onClick_G_4(final View view) {
        soundPool.play(note_G4, 1, 1, 0, 0, 1);
        if(right_aswer==5 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON G!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_Gis_4(final View view) {
        soundPool.play(note_Gis4, 1, 1, 0, 0, 1);
    }

    public void onClick_A_4(final View view) {
        soundPool.play(note_A4, 1, 1, 0, 0, 1);
        if(right_aswer==6 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON A!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }

    public void onClick_Ais_4(final View view) {
        soundPool.play(note_Ais4, 1, 1, 0, 0, 1);
    }

    public void onClick_H_4(final View view) {
        soundPool.play(note_H4, 1, 1, 0, 0, 1);
        if(right_aswer==7 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON H!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }


    public void onClick_C_5(final View view) {
        soundPool.play(note_C5, 1, 1, 0, 0, 1);
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
