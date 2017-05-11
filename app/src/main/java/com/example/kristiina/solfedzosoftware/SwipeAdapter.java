package com.example.kristiina.solfedzosoftware;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


//ViewPager:
//https://www.youtube.com/watch?v=nL0k2usU7I8
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
            textView.setText("MUUSIKA KOOSNEB \n HELIDEST,\n MIDA KIRJUTATAKSE \n NOOTIDENA");
        }else if (position== 1){
            textView.setText("NOOT KOOSNEB: \n NOODIPEAST,\n NOODIVARREST\n JA LIPUKESEST.");
        }else if(position== 2) {
            textView.setText("NOODIJOONESTIK KOOSNEB \n 5 JOONEST \n JA \n 4 JOONEVAHEST.");
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
