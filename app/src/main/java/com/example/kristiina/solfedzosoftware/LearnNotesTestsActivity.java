package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LearnNotesTestsActivity extends AppCompatActivity {

    public static final String PREFERENCES = "Preferences";

    private String answer;
    private int score;
    private int questionNumber = 0;

    TextView bestScoreNotes;

    private ImageView star1, star2, star3, star4, star5, star6, star7, star8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_tests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        star1= (ImageView) findViewById(R.id.star1);
        star2= (ImageView) findViewById(R.id.star2);
        star3= (ImageView) findViewById(R.id.star3);
        star4= (ImageView) findViewById(R.id.star4);
        star5= (ImageView) findViewById(R.id.star5);
        star6= (ImageView) findViewById(R.id.star6);
        star7= (ImageView) findViewById(R.id.star7);
        star8= (ImageView) findViewById(R.id.star8);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreNotes",0);

        bestScoreNotes = (TextView) findViewById(R.id.bestScoreNotes);
        bestScoreNotes.setText("PARIM TULEMUS :");

        showStars(bestScore);
        LearnNotesTests2Activity.right=0;

    }

    public void onClick_Start_Test_Notes(View v){
        Intent newActivity = new Intent(this,LearnNotesTests2Activity.class);
        startActivity(newActivity);

    }

    public void showStars(int bestScore){

        if (bestScore==1){
            star1.setImageResource(R.drawable.yellow_star);
        }else if(bestScore==2){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
        }else if(bestScore==3){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
            star3.setImageResource(R.drawable.yellow_star);
        }else if(bestScore==4){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
            star3.setImageResource(R.drawable.yellow_star);
            star4.setImageResource(R.drawable.yellow_star);

        }else if(bestScore==5){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
            star3.setImageResource(R.drawable.yellow_star);
            star4.setImageResource(R.drawable.yellow_star);
            star5.setImageResource(R.drawable.yellow_star);

        }else if(bestScore==6){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
            star3.setImageResource(R.drawable.yellow_star);
            star4.setImageResource(R.drawable.yellow_star);
            star5.setImageResource(R.drawable.yellow_star);
            star6.setImageResource(R.drawable.yellow_star);

        }else if(bestScore==7){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
            star3.setImageResource(R.drawable.yellow_star);
            star4.setImageResource(R.drawable.yellow_star);
            star5.setImageResource(R.drawable.yellow_star);
            star6.setImageResource(R.drawable.yellow_star);
            star7.setImageResource(R.drawable.yellow_star);

        }else if(bestScore==8){
            star1.setImageResource(R.drawable.yellow_star);
            star2.setImageResource(R.drawable.yellow_star);
            star3.setImageResource(R.drawable.yellow_star);
            star4.setImageResource(R.drawable.yellow_star);
            star5.setImageResource(R.drawable.yellow_star);
            star6.setImageResource(R.drawable.yellow_star);
            star7.setImageResource(R.drawable.yellow_star);
            star8.setImageResource(R.drawable.yellow_star);

        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreNotes",0);

        bestScoreNotes = (TextView) findViewById(R.id.bestScoreNotes);
        bestScoreNotes.setText("PARIM TULEMUS : ");
        showStars(bestScore);

        LearnNotesTests2Activity.right=0;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }

}
