package com.example.kristiina.solfedzosoftware;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SwipeAdapter extends PagerAdapter{

    private int[] images = {R.drawable.ic_left_arrow_img, R.drawable.ic_play_arrow_img,R.drawable.ic_play_arrow_img};
    private Context context;
    LayoutInflater layoutInflater;

    public SwipeAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(FrameLayout)object);
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
            textView.setText("MUUSIKA koosneb helidest, mida kirjutatakse nootidena.");
        }else if (position== 1){
            textView.setText("NOOT KOOSNEB noodipeast, noodivarrest ja lipukesest.");
        }else if(position== 2) {
            textView.setText("PAUS on vaikuse märk.");
        } else{
            textView.setText("Image : " + position);
        }

        if(position==0){
            swipeLeftArrow.setVisibility(View.INVISIBLE);
        }else{
            swipeLeftArrow.setVisibility(View.VISIBLE);
        }

        if(position==2){
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
