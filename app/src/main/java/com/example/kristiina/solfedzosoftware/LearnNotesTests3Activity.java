package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//Quiz:
//https://www.youtube.com/watch?v=3sDo9afuPOI
//https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
//Shared preferences:
//https://developer.android.com/training/basics/data-storage/shared-preferences.html
public class LearnNotesTests3Activity extends AppCompatActivity {


    TextView resultTextView;
    public static final String PREFERENCES = "Preferences";
    private ImageView star1, star2, star3, star4, star5, star6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_tests3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Shared preferences:
        //https://developer.android.com/training/basics/data-storage/shared-preferences.html
        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreNotes",0);

        //Quiz:
        //https://www.youtube.com/watch?v=3sDo9afuPOI
        //https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
        resultTextView = (TextView) findViewById(R.id.resultN);
        resultTextView.setText("ÕIGETE VASTUSTE ARV: " + LearnNotesTests2Activity.right + "\n VALEDE VASTUSTE ARV: "+ (6-LearnNotesTests2Activity.right)) ;

        star1= (ImageView) findViewById(R.id.star1R);
        star2= (ImageView) findViewById(R.id.star2R);
        star3= (ImageView) findViewById(R.id.star3R);
        star4= (ImageView) findViewById(R.id.star4R);
        star5= (ImageView) findViewById(R.id.star5R);
        star6= (ImageView) findViewById(R.id.star6R);

        showStars(bestScore);


    }

    private void showStars(int bestScore){

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

        }

    }

    //Quiz:
    //https://www.youtube.com/watch?v=3sDo9afuPOI
    //https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
    public void onClick_Start_Test_N(View v){
        Intent intent= new Intent(v.getContext(), LearnNotesTests2Activity.class);
        startActivity(intent);
        finish();
    }

    //Quiz:
    //https://www.youtube.com/watch?v=3sDo9afuPOI
    //https://drive.google.com/file/d/0B7WV-6S7pAvVcmRWaU1UWVYzNUk/view
    @Override
    protected void onPause(){
        super.onPause();
        LearnNotesTests2Activity.right=0;

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }
}
