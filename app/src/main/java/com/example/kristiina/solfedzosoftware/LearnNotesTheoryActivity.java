package com.example.kristiina.solfedzosoftware;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

//ViewPager:
//https://www.youtube.com/watch?v=nL0k2usU7I8
//http://stackoverflow.com/a/35253674
public class LearnNotesTheoryActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SwipeAdapter swipeAdapter;


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
    //http://stackoverflow.com/a/35253674
    public void onClick_rightArrow(View v){
        int pagenumber= viewPager.getCurrentItem();
        pagenumber++;
        viewPager.setCurrentItem(pagenumber);


    }
    //http://stackoverflow.com/a/35253674
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
