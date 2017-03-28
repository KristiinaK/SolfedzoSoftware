package com.example.kristiina.solfedzosoftware;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LearnRythmsByListening extends AppCompatActivity {

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

    int button_voice;

    private Handler handler;
    private long time;
    TextView numbersTextview;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_by_listening);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        numbersTextview = (TextView) findViewById(R.id.numbersTextview);


        //To connect mobile volume button with app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadSounds();


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

    protected void loadSounds() {

        button_voice = soundPool.load(this, R.raw.button_voice, 1);
    }

    public void onClick_btn_play_rythms(View v){
        time=1000;
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("VIVZ","RUN WAS CALLED");

                int number= (int)time/1000;
                numbersTextview.setText(number + "");

                time = time+1000;
                if(time <=5000){
                    handler.postDelayed(this,1000);
                }else{
                    numbersTextview.setText("");
                }

            }
        };

        handler.postDelayed(runnable,1000);

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
