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
        note_C = soundPool.load(this, R.raw.c,1);
        note_D = soundPool.load(this, R.raw.d,1);
        note_E = soundPool.load(this, R.raw.e,1);
        note_F = soundPool.load(this, R.raw.f,1);
        note_G = soundPool.load(this, R.raw.g,1);
        note_A = soundPool.load(this, R.raw.a,1);
        note_H = soundPool.load(this, R.raw.h,1);
    }


    int right_aswer;
    boolean playbuttonpressed;

    public void onClick_btn_play(final View view){

        playbuttonpressed=true;

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

    public  void onClickButtonNext(final View view){
        playbuttonpressed=false;
        Random random= new Random();
        right_aswer=random.nextInt(7)+1;

        answer_textview.setText("");
        playbutton.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
    }

    public void onClick_C(final View view) {
        soundPool.play(note_C, 1, 1, 0, 0, 1);

        if(right_aswer==1 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON C!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }

    public void onClick_D(final View view) {
        soundPool.play(note_D, 1, 1, 0, 0, 1);
        if(right_aswer==2 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON D!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_E(final View view) {
        soundPool.play(note_E, 1, 1, 0, 0, 1);
        if(right_aswer==3 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON E!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_F(final View view) {
        soundPool.play(note_F, 1, 1, 0, 0, 1);
        if(right_aswer==4 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON F!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_G(final View view) {
        soundPool.play(note_G, 1, 1, 0, 0, 1);
        if(right_aswer==5 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON G!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_A(final View view) {
        soundPool.play(note_A, 1, 1, 0, 0, 1);
        if(right_aswer==6 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON A!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_H(final View view) {
        soundPool.play(note_H, 1, 1, 0, 0, 1);
        if(right_aswer==7 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON H!");
            playbutton.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }


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
