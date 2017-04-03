package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LearnNotesTestsActivity extends AppCompatActivity {

    //private TestQuestions testQuestions = new TestQuestions();

    public static final String PREFERENCES = "Preferences";

    private String answer;
    private int score;
    private int questionNumber = 0;

    TextView bestScoreNotes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_tests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreNotes",0);

        bestScoreNotes = (TextView) findViewById(R.id.bestScoreNotes);
        bestScoreNotes.setText("PARIM TULEMUS : "+ bestScore);

        LearnNotesTests2Activity.right=0;

    }

    public void onClick_Start_Test_Notes(View v){
        Intent newActivity = new Intent(this,LearnNotesTests2Activity.class);
        startActivity(newActivity);

    }


    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreNotes",0);

        bestScoreNotes = (TextView) findViewById(R.id.bestScoreNotes);
        bestScoreNotes.setText("PARIM TULEMUS : "+ bestScore);

        LearnNotesTests2Activity.right=0;
    }


}
