package com.example.kristiina.solfedzosoftware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsNotesByPicture extends AppCompatActivity {
    public static final String PREFERENCES = "Preferences";

    private RadioGroup radioGroup;
    private RadioButton checkedRadioButton;

    private RadioButton viiulivoti;
    private RadioButton bassivoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notes_by_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        radioGroup = (RadioGroup) findViewById(R.id.settingsNotesByPictureRadioGroup);

        viiulivoti = (RadioButton) findViewById(R.id.viiulivotiRadio);
        bassivoti = (RadioButton) findViewById(R.id.bassivotiRadio);

        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);

        String settings= preferences.getString("settingsNotesByPicture","");


        if (settings.equals("VIIULIVÕTI")) {
            viiulivoti.setChecked(true);
        }else if(settings.equals("BASSIVÕTI")){
            bassivoti.setChecked(true);
        }else{
            viiulivoti.setChecked(true);
        }


    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        SharedPreferences.Editor editor = preferences.edit();

        int selectedItem= radioGroup.getCheckedRadioButtonId();
        checkedRadioButton = (RadioButton) findViewById(selectedItem);


        editor.putString("settingsNotesByPicture", checkedRadioButton.getText().toString());
        editor.commit();

        Intent intent=new Intent(this,LearnNotesByPictureActivity.class);
        startActivity(intent);

    }

}
