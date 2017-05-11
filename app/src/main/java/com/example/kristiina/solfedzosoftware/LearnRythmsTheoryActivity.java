package com.example.kristiina.solfedzosoftware;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
//ViewPager:
//https://www.youtube.com/watch?v=nL0k2usU7I8
//http://stackoverflow.com/a/35253674
public class LearnRythmsTheoryActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MySwipeAdapter swipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_theory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        viewPager = (ViewPager) findViewById(R.id.viewPagerLearnRythms);
        swipeAdapter = new MySwipeAdapter(this);
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


    //ViewPager:
    //https://www.youtube.com/watch?v=nL0k2usU7I8
    private class MySwipeAdapter extends PagerAdapter {

        private int[] images = {R.drawable.ic_left_arrow_img, R.drawable.ic_play_arrow_img, R.drawable.ic_play_arrow_img};
        private Context context;
        LayoutInflater layoutInflater;

        public MySwipeAdapter(Context context){
            this.context=context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.swipe_theory_notes_1,container,false);

            ImageView imageView = (ImageView) view.findViewById(R.id.swipe_image);
            TextView textView = (TextView) view.findViewById(R.id.swipe_textview);
            TextView pageNumber = (TextView) view.findViewById(R.id.pageNumber);

            ImageView swipeLeftArrow = (ImageView) view.findViewById(R.id.swipeLeftArrow);
            ImageView swipeRightArrow = (ImageView) view.findViewById(R.id.swipeRightArrow);


            imageView.setImageResource(images[position]);
            if(position==0) {
                textView.setText("RÜTM ON\nPIKEMATE JA\nLÜHEMATE HELIDE\n JÄRGNEVUS");
                textView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            }else if (position== 1){
                textView.setText("NOODIPIKKUST\nEHK VÄLTUST\nMÄÄRAME LÖÖKIDES");
            }else if(position== 2) {
                textView.setText("RÜTMISILBID ON:\nTA\nTI-TI\nTIRI-TIRI\nTI-TIRI\nTIRI-TI\nTAI-RI\nTA-I-TI\nTA-A\nTA-A-A\nTA-A-A-A.");
                textView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            }else{
                textView.setText("Image : " + position);
            }

            if(position==0){
                swipeLeftArrow.setVisibility(View.INVISIBLE);
            }else{
                swipeLeftArrow.setVisibility(View.VISIBLE);
            }

            if(position==3){
                swipeRightArrow.setVisibility(View.INVISIBLE);
            }else{
                swipeRightArrow.setVisibility(View.VISIBLE);
            }
            pageNumber.setText((position+1)+"/"+images.length);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((FrameLayout)object);
        }
    }
}