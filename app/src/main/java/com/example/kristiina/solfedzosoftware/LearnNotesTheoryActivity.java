package com.example.kristiina.solfedzosoftware;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class LearnNotesTheoryActivity extends AppCompatActivity {

    ViewPager viewPager;
    SwipeAdapter swipeAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_theory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        viewPager = (ViewPager) findViewById(R.id.viewPagerLearnNotes);
        swipeAdapter = new SwipeAdapter(this);
        viewPager.setAdapter(swipeAdapter);


    }

    public void onClick_rightArrow(View v){
        int pagenumber= viewPager.getCurrentItem();
        pagenumber++;
        viewPager.setCurrentItem(pagenumber);


    }

    public void onClick_leftArrow(View v){
        int pagenumber= viewPager.getCurrentItem();
        if(pagenumber==0){
            viewPager.setCurrentItem(pagenumber);

        } else {
            pagenumber--;
            viewPager.setCurrentItem(pagenumber);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();    }
}
