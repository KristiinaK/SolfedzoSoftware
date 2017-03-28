package com.example.kristiina.solfedzosoftware;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class LearnRythmsByListening extends AppCompatActivity {

    private Handler handler;
    private long timeRemaining = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_by_listening);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("VIVZ","RUN WAS CALLED");
                timeRemaining = timeRemaining-1000;
                if(timeRemaining >0){
                    handler.postDelayed(this,1000);
                }

            }
        };

        handler.postDelayed(runnable,1000);
    }

}
