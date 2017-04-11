package com.example.kristiina.solfedzosoftware;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class LearnNotesTests3Activity extends AppCompatActivity {

    TextView resultTextView;
    public static final String PREFERENCES = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_tests3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreNotes",0);

        resultTextView = (TextView) findViewById(R.id.resultN);
        resultTextView.setText("ÕIGETE VASTUSTE ARV: " + LearnNotesTests2Activity.right + "\n VALEDE VASTUSTE ARV: "+ (2-LearnNotesTests2Activity.right) + "\n PARIM TULEMUS: "+ bestScore);




    }
    public void onClick_Start_Test_N(View v){
        Intent intent= new Intent(v.getContext(), LearnNotesTests2Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        LearnNotesTests2Activity.right=0;

    }

}
