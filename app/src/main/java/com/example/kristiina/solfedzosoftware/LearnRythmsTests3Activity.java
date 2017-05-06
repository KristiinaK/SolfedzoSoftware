package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LearnRythmsTests3Activity extends AppCompatActivity {

    private TextView resultTextView;
    public static final String PREFERENCES = "Preferences";
    private ImageView star1, star2, star3, star4, star5, star6, star7, star8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_tests3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        int bestScore= preferences.getInt("scoreRythms",0);

        resultTextView = (TextView) findViewById(R.id.resultRythms);
        resultTextView.setText("ÕIGETE VASTUSTE ARV: " + LearnRythmsTests2Activity.right + "\n VALEDE VASTUSTE ARV: "+ (2-LearnRythmsTests2Activity.right)) ;



        star1= (ImageView) findViewById(R.id.RythmsStar1R);
        star2= (ImageView) findViewById(R.id.RythmsStar2R);
        star3= (ImageView) findViewById(R.id.RythmsStar3R);
        star4= (ImageView) findViewById(R.id.RythmsStar4R);
        star5= (ImageView) findViewById(R.id.RythmsStar5R);
        star6= (ImageView) findViewById(R.id.RythmsStar6R);
        star7= (ImageView) findViewById(R.id.RythmsStar7R);
        star8= (ImageView) findViewById(R.id.RythmsStar8R);
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

    public void onClick_Start_Test_R(View v){
        Intent intent= new Intent(v.getContext(), LearnRythmsTests2Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        LearnRythmsTests2Activity.right=0;

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }

}
