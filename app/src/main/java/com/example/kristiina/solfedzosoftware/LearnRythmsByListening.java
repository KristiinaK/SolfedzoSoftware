package com.example.kristiina.solfedzosoftware;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class LearnRythmsByListening extends AppCompatActivity {

    SoundPool soundPool;
    SoundPool soundPool2;
    SoundPool.Builder soundPoolBuilder;
    SoundPool.Builder soundPoolBuilder2;

    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

    int voice;
    int metronom;

    private Handler handler;
    private long time;
    TextView numbersTextview;


    TextView answer_textview;
    TextView nextbutton;
    LinearLayout playbuttonRythms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_by_listening);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        playbuttonRythms = (LinearLayout) findViewById(R.id.playButtonRythms);
        numbersTextview = (TextView) findViewById(R.id.numbersTextview);


        //To connect mobile volume button with app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadSounds();



        answer_textview = (TextView) findViewById(R.id.answerTextView);
        nextbutton= (TextView) findViewById(R.id.ButtonNextRythms);

        //To connect mobile volume button with app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        playbuttonRythms.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.GONE);


        right_aswer=2;

    }

    int right_aswer;
    boolean playbuttonpressed;

    protected void createSoundPool(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            attributeBuilder= new AudioAttributes.Builder();
            attributeBuilder.setUsage(AudioAttributes.USAGE_GAME);
            attributeBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            attributes=attributeBuilder.build();

            soundPoolBuilder = new SoundPool.Builder();
            soundPoolBuilder.setAudioAttributes(attributes);
            soundPool=soundPoolBuilder.build();

            soundPoolBuilder2 = new SoundPool.Builder();
            soundPoolBuilder2.setAudioAttributes(attributes);
            soundPool2=soundPoolBuilder2.build();

        }else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
            soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
    }

    protected void loadSounds() {

        voice = soundPool2.load(this, R.raw.button_voice, 1);
        metronom = soundPool.load(this, R.raw.rythm_button, 1);
    }



    int add_time;
    int in_the_loop;
    int condition_time;

    public void onClick_btn_play_rythms(View v){
        playbuttonRythms.setEnabled(false);
        playbuttonpressed=true;

        time=0;
        add_time=1000;
        in_the_loop=0;
        condition_time=6000;

        numbersTextview.setText("");
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("VIVZ","RUN WAS CALLED");

                time = time + add_time;
                if(time <= 4000){

                    int number= (int)time/1000;
                    numbersTextview.setText(number + "");

                    soundPool.play(metronom, 1, 1, 0, 0, 1);
                    handler.postDelayed(this,1000);


                }else if(time < condition_time){

                    Log.d("VIVZ",time+"");
                    if(right_aswer == 1){
                        //TA
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                    }else if(right_aswer == 2){
                        //TI-TI
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        add_time=500;
                    } else if(right_aswer==3){
                        //TIRI-TIRI
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        add_time=250;
                    }
                    else if(right_aswer==4){
                        //TI-TIRI
                        if(in_the_loop==0){
                            add_time=500;

                        }else if(in_the_loop==1){
                            add_time=250;
                        }else{
                            add_time=250;
                        }
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        in_the_loop+=1;
                    }
                    else if(right_aswer==5){
                        //TIRI-TI
                        if(in_the_loop==0){
                            add_time=250;

                        }else if(in_the_loop==1){
                            add_time=250;
                        }else{
                            add_time=500;
                        }
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        in_the_loop+=1;
                    }else if(right_aswer==6){
                        //TAI-RI
                        if(in_the_loop==0){
                            add_time=750;

                        }else {
                            add_time=250;
                        }
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        in_the_loop+=1;
                    }
                    else if(right_aswer==7){
                        //TA-A
                        if(in_the_loop==0){
                            add_time=1000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                            condition_time=7000;

                        }else {
                            add_time=1000;
                        }

                        in_the_loop+=1;
                    }else if(right_aswer==8){
                        //TA-A-A
                        if(in_the_loop==0){
                            add_time=1000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                            condition_time=8000;

                        }else {
                            add_time=1000;
                        }

                        in_the_loop+=1;
                    }else if(right_aswer==9){
                        //TA-A-A-A
                        if(in_the_loop==0){
                            add_time=1000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                            condition_time=9000;

                        }else {
                            add_time=1000;
                        }

                        in_the_loop+=1;
                    }else if(right_aswer==10){
                        //TA-I-TI
                        if(in_the_loop==0){
                            add_time=1000;
                            condition_time=7000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                        }else if(in_the_loop==1) {
                            add_time=500;

                        } else{
                            add_time=500;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                        }

                        in_the_loop+=1;
                    }

                    numbersTextview.setText("");
                    soundPool.play(metronom, 1, 1, 0, 0, 1);
                    handler.postDelayed(this,add_time);
                    playbuttonRythms.setEnabled(false);
                }else{
                    playbuttonRythms.setEnabled(true);
                }

            }
        };

        handler.postDelayed(runnable,add_time);


    }

    public  void onClickButtonNext(final View view){
        playbuttonpressed=false;
        Random random= new Random();
        right_aswer=random.nextInt(10)+1;

        answer_textview.setText("");
        playbuttonRythms.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
    }



    public void onClick_TA(View v){
        if(right_aswer==1 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }

    public void onClick_TI_TI(View v){
        if(right_aswer==2 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TI-TI!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TIRI_TIRI(View v){
        if(right_aswer==3 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TIRI-TIRI!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TI_TIRI(View v){
        if(right_aswer==4 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TI-TIRI!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TIRI_TI(View v){
        if(right_aswer==5 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TIRI-TI!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TAI_RI(View v){
        if(right_aswer==6 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TAI-RI!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_A(View v){
        if(right_aswer==7 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-A!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_A_A(View v){
        if(right_aswer==8 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-A-A!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_A_A_A(View v){
        if(right_aswer==9 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-A-A-A!");
            playbuttonRythms.setVisibility(View.GONE);
            nextbutton.setVisibility(View.VISIBLE);
        } else if (playbuttonpressed){
            answer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }
    }
    public void onClick_TA_I_TI(View v){
        if(right_aswer==10 && playbuttonpressed){
            answer_textview.setText("TUBLI :) ÕIGE VASTUS ON TA-I-TI!");
            playbuttonRythms.setVisibility(View.GONE);
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
        loadSounds();
    }

}
