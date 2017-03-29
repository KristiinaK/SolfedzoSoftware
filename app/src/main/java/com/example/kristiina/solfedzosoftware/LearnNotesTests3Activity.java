package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class LearnNotesTests3Activity extends AppCompatActivity {

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_tests3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        resultTextView = (TextView) findViewById(R.id.resultN);
        resultTextView.setText("ÕIGETE VASTUSTE ARV: " + LearnNotesTests2Activity.right + "\n VALEDE VASTUSTE ARV: "+ LearnNotesTests2Activity.wrong);

        LearnNotesTests2Activity.right=0;
        LearnNotesTests2Activity.wrong=0;
    }
    public void onClick_Start_Test_N(View v){
        Intent intent= new Intent(v.getContext(), LearnNotesTestsActivity.class);
        startActivity(intent);
    }

}
