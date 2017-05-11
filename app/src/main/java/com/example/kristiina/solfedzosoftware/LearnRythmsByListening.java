package com.example.kristiina.solfedzosoftware;


import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

//Soundpool:
//https://www.youtube.com/watch?v=wQ2DKxNtrT4
//https://www.youtube.com/watch?v=byNOLwmzNz0
//https://www.youtube.com/watch?v=bslTj2zDARc

public class LearnRythmsByListening extends AppCompatActivity {

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
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
    ImageView img_animation;
    ImageView img_animation2;

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

        //Soundpool:
        //https://www.youtube.com/watch?v=wQ2DKxNtrT4
        //https://www.youtube.com/watch?v=byNOLwmzNz0
        //https://www.youtube.com/watch?v=bslTj2zDARc
        createSoundPool();
        loadSounds();


        img_animation = (ImageView) findViewById(R.id.img_animation);
        img_animation2 = (ImageView) findViewById(R.id.img_animation2);
        answer_textview = (TextView) findViewById(R.id.answerTextView);
        nextbutton= (TextView) findViewById(R.id.ButtonNextRythms);

        //To connect mobile volume button with app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        playbuttonRythms.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.GONE);

        right_aswer=2;

    }

    int right_aswer;
    private boolean playbuttonpressed;

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

            soundPoolBuilder2 = new SoundPool.Builder();
            soundPoolBuilder2.setAudioAttributes(attributes);
            soundPool2=soundPoolBuilder2.build();

        }else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
            soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
    }

    //Soundpool:
    //https://www.youtube.com/watch?v=wQ2DKxNtrT4
    //https://www.youtube.com/watch?v=byNOLwmzNz0
    //https://www.youtube.com/watch?v=bslTj2zDARc
    protected void loadSounds() {
        //Sound downloaded from: https://www.soundjay.com/button-sounds-2.html
        voice = soundPool2.load(this, R.raw.button_voice, 1);
        // Sound downloaded from: https://www.soundjay.com/button-sounds-5.html
        metronom = soundPool.load(this, R.raw.rythm_button, 1);
    }


    int add_time;
    int in_the_loop;
    int condition_time;

    //https://developer.android.com/reference/android/view/animation/TranslateAnimation.html
    //https://www.youtube.com/watch?v=oCRNaYaEzqE
    public void onClick_btn_play_rythms(View v){

        numbersTextview.setVisibility(View.VISIBLE);
        //Hand image from https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRy4JZuZF2e7MSxop71U3dkSr3--Qhw8z8lzveMAYf6WKsfSZ4z
        img_animation.setVisibility(View.GONE);
        img_animation2.setVisibility(View.GONE);

        playbuttonRythms.setEnabled(false);
        playbuttonpressed=true;

        time=1000;
        add_time=1000;
        in_the_loop=0;
        condition_time=6000;

        numbersTextview.setText("");
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time = time + add_time;
                if(time <= 4000){

                    int number= (int)time/1000;

                    switch (number){
                        case 2: numbersTextview.setText("3");
                            break;
                        case 3: numbersTextview.setText("2");
                            break;
                        case 4: numbersTextview.setText("1");
                            break;
                    }

                    soundPool.play(metronom, 1, 1, 0, 0, 1);
                    handler.postDelayed(this,1000);


                }else if(time < condition_time){
                    numbersTextview.setVisibility(View.GONE);
                    img_animation.setVisibility(View.VISIBLE);
                    img_animation2.setVisibility(View.VISIBLE);

                    if(right_aswer == 1){
                        //TA
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                    }else if(right_aswer == 2){
                        //TI-TI
                        soundPool2.play(voice, 1, 1, 0, 0, 1);

                        TranslateAnimation animation = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0.4f,
                                Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f);
                        animation.setDuration(500);
                        animation.setRepeatCount(0);
                        animation.setRepeatMode(1);


                        TranslateAnimation animation2 = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF,-0.4f,
                                Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_PARENT,0.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f);
                        animation2.setDuration(500);
                        animation2.setRepeatCount(0);
                        animation2.setRepeatMode(1);


                        img_animation.startAnimation(animation);
                        img_animation2.startAnimation(animation2);
                        add_time=500;
                    } else if(right_aswer==3){
                        //TIRI-TIRI
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        TranslateAnimation animation = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0.4f,
                                Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f);
                        animation.setDuration(250);
                        animation.setRepeatCount(0);
                        animation.setRepeatMode(1);


                        TranslateAnimation animation2 = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF,-0.4f,
                                Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_PARENT,0.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f);
                        animation2.setDuration(250);
                        animation2.setRepeatCount(0);
                        animation2.setRepeatMode(1);


                        img_animation.startAnimation(animation);
                        img_animation2.startAnimation(animation2);
                        add_time=250;

                    }
                    else if(right_aswer==4){
                        //TI-TIRI
                        if(in_the_loop==0){
                            add_time=500;
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(500);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);

                        }else if(in_the_loop==1){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(250);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(250);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=250;
                        }else{
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(250);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(250);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=250;
                        }
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        in_the_loop+=1;
                    }
                    else if(right_aswer==5){
                        //TIRI-TI
                        if(in_the_loop==0){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(250);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(250);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=250;

                        }else if(in_the_loop==1){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(250);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(250);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=250;
                        }else{
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(500);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=500;
                        }
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        in_the_loop+=1;
                    }else if(right_aswer==6){
                        //TAI-RI
                        if(in_the_loop==0){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(750);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);

                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(750);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=750;

                        }else {
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(250);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(250);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=250;
                        }
                        soundPool2.play(voice, 1, 1, 0, 0, 1);
                        in_the_loop+=1;
                    }
                    else if(right_aswer==7){
                        //TA-A
                        if(in_the_loop==0){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);

                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                            condition_time=7000;


                        }else {
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);




                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                        }

                        in_the_loop+=1;
                    }else if(right_aswer==8){
                        //TA-A-A
                        if(in_the_loop==0){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                            condition_time=8000;

                        }else {
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                        }

                        in_the_loop+=1;
                    }else if(right_aswer==9){
                        //TA-A-A-A
                        if(in_the_loop==0){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);




                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);

                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                            condition_time=9000;

                        }else {
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                        }

                        in_the_loop+=1;
                    }else if(right_aswer==10){
                        //TA-I-TI
                        if(in_the_loop==0){
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(1000);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(0);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, -0.4f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(1000);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(0);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=1000;
                            condition_time=7000;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                        }else if(in_the_loop==1) {
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(500);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=500;

                        } else{
                            TranslateAnimation animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            animation.setRepeatMode(1);


                            TranslateAnimation animation2 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF,-0.4f,
                                    Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_PARENT,0.0f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);
                            animation2.setDuration(500);
                            animation2.setRepeatCount(0);
                            animation2.setRepeatMode(1);


                            img_animation.startAnimation(animation);
                            img_animation2.startAnimation(animation2);
                            add_time=500;
                            soundPool2.play(voice, 1, 1, 0, 0, 1);
                        }

                        in_the_loop+=1;
                    }

                    numbersTextview.setText("");
                    if((time%1000)==0){
                        soundPool.play(metronom, 1, 1, 0, 0, 1);
                    }

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
        numbersTextview.setVisibility(View.VISIBLE);
        img_animation.setVisibility(View.GONE);
        img_animation2.setVisibility(View.GONE);

        Random random= new Random();
        right_aswer=random.nextInt(10)+1;
        playbuttonRythms.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
        right_ans_clicked=false;
        right_btn_id.setBackgroundResource(R.drawable.piano_white_key);
        answer_textview.setBackgroundResource(0);
        playbuttonpressed=false;
        answer_textview.setText("");

    }


    private Button userClickedButtonId;
    private boolean right_ans_clicked=false;
    private Button right_btn_id;

    public void onClick_TA(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__ta);
        if(right_aswer==1 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    public void onClick_TI_TI(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__titi);
        if(right_aswer==2 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TIRI_TIRI(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__tiri__tiri);
        if(right_aswer==3 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TI_TIRI(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__ti__tiri);
        if(right_aswer==4 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TIRI_TI(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__tiri__ti);
        if(right_aswer==5 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TAI_RI(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__tai__ri);
        if(right_aswer==6 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TA_A(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__taa);
        if(right_aswer==7 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TA_A_A(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__taaa);
        if(right_aswer==8 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TA_A_A_A(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__taaaa);
        if(right_aswer==9 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }
    public void onClick_TA_I_TI(View v){
        userClickedButtonId = (Button)findViewById(R.id.btn__ta__i__ti);
        if(right_aswer==10 && playbuttonpressed && !(right_ans_clicked)){
            rightAnswer();
        } else if (playbuttonpressed && !(right_ans_clicked)){
            falseAnswer();
        }
    }

    private void rightAnswer(){
        right_ans_clicked=true;

        //http://stackoverflow.com/a/26894146
        answer_textview.setText("ÕIGE VASTUS "+ new String(Character.toChars(0x1F60A)));

        playbuttonRythms.setVisibility(View.GONE);
        nextbutton.setVisibility(View.VISIBLE);
        userClickedButtonId.setBackgroundResource(R.drawable.piano_green_key);
        answer_textview.setBackgroundResource(R.color.green);
        right_btn_id=userClickedButtonId;
    }
    private void falseAnswer(){
        answer_textview.setText("PROOVI VEEL");
        answer_textview.setBackgroundResource(R.color.pink);
    }

    @Override
    protected void onPause(){
        super.onPause();
        soundPool.release();

    }
    @Override
    protected void onResume(){
        super.onResume();
        //Soundpool:
        //https://www.youtube.com/watch?v=wQ2DKxNtrT4
        //https://www.youtube.com/watch?v=byNOLwmzNz0
        //https://www.youtube.com/watch?v=bslTj2zDARc
        createSoundPool();
        loadSounds();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }
}
