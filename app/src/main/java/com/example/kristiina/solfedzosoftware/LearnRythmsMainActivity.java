package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LearnRythmsMainActivity extends AppCompatActivity {

    private final int[] images ={ R.drawable.ic_note_img,R.drawable.ic_volume_img,R.drawable.ic_book_img, R.drawable.ic_quiz_img,};

    private final String[] titles = {"ÕPI RÜTME PILDI JÄRGI","ÕPI RÜTME KUULMISE JÄRGI", "TEOORIA", "TESTI TEADMISI"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rythms_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ListView listView = (ListView) findViewById(R.id.listViewLearnRythms);

        LearnRythmsMainActivity.CustomAdapter customAdapter= new LearnRythmsMainActivity.CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    Intent intent= new Intent(view.getContext(), LearnRythmsByPicture.class);
                    startActivity(intent);
                } else if (position==1){
                    Intent intent= new Intent(view.getContext(), LearnRythmsByListening.class);
                    startActivity(intent);
                } else if (position==2){
                    Intent intent= new Intent(view.getContext(), LearnNotesTheoryActivity.class);
                    startActivity(intent);
                } else if (position==3){
                    Intent intent= new Intent(view.getContext(), LearnRythmsTestsActivity.class);
                    startActivity(intent);
                }

            }

        });

    }
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list_item_opi_noote, null);
            ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView);
            TextView textView=(TextView)convertView.findViewById(R.id.text4);

            imageView.setImageResource(images[position]);
            textView.setText(titles[position]);

            return convertView;
        }
    }

}
