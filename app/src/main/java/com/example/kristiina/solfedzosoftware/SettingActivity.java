package com.example.kristiina.solfedzosoftware;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingActivity extends AppCompatActivity {

    public static final String PREFERENCES = "Preferences";

    private RadioGroup radioGroup;
    private RadioButton checkedRadioButton;

    private RadioButton CDEH;
    private RadioButton CDEB;
    private RadioButton DOREMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        radioGroup = (RadioGroup) findViewById(R.id.settingsRadioGroup);

        CDEH = (RadioButton) findViewById(R.id.CDEH);
        CDEB = (RadioButton) findViewById(R.id.CDEB);
        DOREMI = (RadioButton) findViewById(R.id.doReMi);

        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);

        String settings= preferences.getString("settings","");


        if (settings.equals("C, D, E, F, G, A, H")) {
            CDEH.setChecked(true);
        }else if(settings.equals("C, D, E, F, G, A, B")){
            CDEB.setChecked(true);
        } else if (settings.equals("DO, RE, MI, FA, SOL, LA, SI")) {
            DOREMI.setChecked(true);
        }else{
            CDEH.setChecked(true);
        }

    }


    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        SharedPreferences.Editor editor = preferences.edit();

        int selectedItem= radioGroup.getCheckedRadioButtonId();
        checkedRadioButton = (RadioButton) findViewById(selectedItem);


        editor.putString("settings", checkedRadioButton.getText().toString());
        editor.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }


}
