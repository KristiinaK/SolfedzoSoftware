package com.example.kristiina.solfedzosoftware;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

    TextView playbutton;
    LinearLayout linearLayout;

    int note_C;
    int note_D;
    int note_E;
    int note_F;
    int note_G;
    int note_A;
    int note_H;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opi_noote_kuulmise_jargi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        playbutton = (TextView) findViewById(R.id.playButton);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        //To connect mobile volume button with app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

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
        note_C = soundPool.load(this, R.raw.c,1);
        note_D = soundPool.load(this, R.raw.d,1);
        note_E = soundPool.load(this, R.raw.e,1);
        note_F = soundPool.load(this, R.raw.f,1);
        note_G = soundPool.load(this, R.raw.g,1);
        note_A = soundPool.load(this, R.raw.a,1);
        note_H = soundPool.load(this, R.raw.h,1);
    }

    int user_answer;
    int right_aswer;
    boolean playbutton_pressed;


    public void onClick_btn_play(final View view){

        if(!playbutton_pressed){
            //Generate random number to choose note
            Random random= new Random();
            right_aswer=random.nextInt(7)+1;
        }

        if(user_answer==right_aswer) {
            //Generate random number to choose note
            Random random= new Random();
            right_aswer=random.nextInt(7)+1;
            linearLayout.setBackgroundColor(Color.parseColor("#ff99cc00"));
            playbutton.setBackgroundResource(R.drawable.ic_play_arrow_img);
            playbutton.setText("");

        }else{

        switch (right_aswer){
            case 1: soundPool.play(note_C, 1, 1, 0, 0, 1);
                    break;
            case 2: soundPool.play(note_D, 1, 1, 0, 0, 1);
                    break;
            case 3: soundPool.play(note_E, 1, 1, 0, 0, 1);
                    break;
            case 4: soundPool.play(note_F, 1, 1, 0, 0, 1);
                    break;
            case 5: soundPool.play(note_G, 1, 1, 0, 0, 1);
                    break;
            case 6 : soundPool.play(note_A, 1, 1, 0, 0, 1);
                    break;
            case 7 : soundPool.play(note_H, 1, 1, 0, 0, 1);
                    break;
            default: soundPool.play(note_C, 1, 1, 0, 0, 1);
                    break;
        }
        }
        playbutton_pressed=true;

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


    public void onClick_C(final View view) {
        soundPool.play(note_C, 1, 1, 0, 0, 1);
        user_answer=1;

        if(playbutton_pressed && right_aswer==1) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON C", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void btn_next(){
        linearLayout.setBackgroundColor(Color.parseColor("#00BbFF"));
        playbutton.setText("JÄRGMINE");
        playbutton.setBackgroundResource(0);
    }

    public void onClick_D(final View view) {
        soundPool.play(note_D, 1, 1, 0, 0, 1);
        user_answer=2;
        if(playbutton_pressed && right_aswer==2) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON D", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void onClick_E(final View view) {
        soundPool.play(note_E, 1, 1, 0, 0, 1);
        user_answer=3;

        if(playbutton_pressed && right_aswer==3) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON E", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void onClick_F(final View view) {
        soundPool.play(note_F, 1, 1, 0, 0, 1);
        user_answer=4;

        if(playbutton_pressed && right_aswer==4) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON F", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void onClick_G(final View view) {
        soundPool.play(note_G, 1, 1, 0, 0, 1);
        user_answer=5;

        if(playbutton_pressed && right_aswer==5) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON G", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void onClick_A(final View view) {
        soundPool.play(note_A, 1, 1, 0, 0, 1);
        user_answer=6;

        if(playbutton_pressed && right_aswer==6) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON A", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void onClick_H(final View view) {
        soundPool.play(note_H, 1, 1, 0, 0, 1);
        user_answer=7;

        if(playbutton_pressed && right_aswer==7) {
            Snackbar.make(view, "TUBLI :) ÕIGE VASTUS ON H", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            btn_next();
        } else if(playbutton_pressed){
            Snackbar.make(view, "VALE VASTUS :( PROOVI VEEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }




}
