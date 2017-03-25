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

public class LearnNotesByPictureActivity extends AppCompatActivity {

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

    int note_C;
    int note_D;
    int note_E;
    int note_F;
    int note_G;
    int note_A;
    int note_H;

    int right_answer;
    TextView nextButton;
    TextView aswer_textview;
    LinearLayout upper;
    LinearLayout bottom;


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


        right_answer=2;

        nextButton= (TextView) findViewById(R.id.nextbutton);
        aswer_textview= (TextView) findViewById(R.id.answer_textview);
        upper= (LinearLayout) findViewById(R.id.upper_linearlayout);
        bottom= (LinearLayout) findViewById(R.id.bottom_linearlayout);

        nextButton.setEnabled(false);
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

    public void onClick_next(final View view){
        Random random= new Random();
        right_answer=random.nextInt(7)+1;
        nextButton.setEnabled(false);
        aswer_textview.setText("");

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


    public void onClick_C(final View view) {
        soundPool.play(note_C, 1, 1, 0, 0, 1);
        if (right_answer == 1){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }
    public void onClick_D(final View view) {
        soundPool.play(note_D, 1, 1, 0, 0, 1);
        if (right_answer == 2){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }


    }
    public void onClick_E(final View view) {
        soundPool.play(note_E, 1, 1, 0, 0, 1);
        if (right_answer == 3){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }
    public void onClick_F(final View view) {
        soundPool.play(note_F, 1, 1, 0, 0, 1);
        if (right_answer == 4){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }
    public void onClick_G(final View view) {
        soundPool.play(note_G, 1, 1, 0, 0, 1);
        if (right_answer == 5){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }
    public void onClick_A(final View view) {
        soundPool.play(note_A, 1, 1, 0, 0, 1);
        if (right_answer == 6){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
        }

    }
    public void onClick_H(final View view) {
        soundPool.play(note_H, 1, 1, 0, 0, 1);
        if (right_answer == 7){
            nextButton.setEnabled(true);
            aswer_textview.setText("TUBLI :) ÕIGE VASTUS!");
        }else{
            aswer_textview.setText("VALE VASTUS :( PROOVI VEEL");
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
