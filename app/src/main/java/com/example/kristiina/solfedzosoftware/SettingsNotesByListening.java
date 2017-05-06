package com.example.kristiina.solfedzosoftware;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsNotesByListening extends AppCompatActivity {
    public static final String PREFERENCES = "Preferences";

    private RadioGroup radioGroup;
    private RadioButton checkedRadioButton;

    private RadioButton firstOctav;
    private RadioButton secondOctav;
    private RadioButton smallOctav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notes_by_listening);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        radioGroup = (RadioGroup) findViewById(R.id.settingsNotesRadioGroup);

        firstOctav = (RadioButton) findViewById(R.id.firstOctav);
        secondOctav = (RadioButton) findViewById(R.id.secondOctav);
        smallOctav = (RadioButton) findViewById(R.id.smallOctav);

        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);

        String settings= preferences.getString("settingsNotes","");


        if (settings.equals("ESIMENE OKTAV")) {
            firstOctav.setChecked(true);
        }else if(settings.equals("TEINE OKTAV")){
            secondOctav.setChecked(true);
        } else if (settings.equals("VÄIKE OKTAV")) {
            smallOctav.setChecked(true);
        }else{
            firstOctav.setChecked(true);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(PREFERENCES,0);
        SharedPreferences.Editor editor = preferences.edit();

        int selectedItem= radioGroup.getCheckedRadioButtonId();
        checkedRadioButton = (RadioButton) findViewById(selectedItem);


        editor.putString("settingsNotes", checkedRadioButton.getText().toString());
        editor.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        finish();
    }

}
