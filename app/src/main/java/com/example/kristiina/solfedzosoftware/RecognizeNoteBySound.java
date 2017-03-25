package com.example.kristiina.solfedzosoftware;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;


public class RecognizeNoteBySound extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btnRecord;
    private MediaRecorder record;
    private String FILE;

    TextView txtView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize_note_by_sound);

        FILE = Environment.getExternalStorageDirectory() + "/tempRecord.3gpp";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        txtView=(TextView) findViewById(R.id.textView6);
        btnRecord =(Button) findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if(btnRecord.getText().toString().equals("Record")){
                    try {
                        startRecord();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    btnRecord.setText("End");
                    txtView.setText("Recording...");
                }else if(btnRecord.getText().toString().equals("End")){
                    stopRecord();
                    btnRecord.setText("Play");
                    txtView.setText("");
                }else if(btnRecord.getText().toString().equals("Play")){
                    try {
                    startPlayback();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    btnRecord.setText("Stop");
                }else {
                    stopPlayback();
                    btnRecord.setText("Record");
                }
            }
        });




    }

    public void startRecord() throws Exception{
        if(record!=null){
            record.release();
        }

        File fileOut = new File(FILE);
        if(fileOut!=null){
            fileOut.delete();
        }
        record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.MIC);
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        record.setOutputFile(FILE);

        record.prepare();
        record.start();

    }
    public void stopRecord(){
        record.stop();
        record.release();

    }
    public void startPlayback() throws Exception{
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer= new MediaPlayer();
        mediaPlayer.setDataSource(FILE);
        mediaPlayer.prepare();
        mediaPlayer.start();
        Toast.makeText(this,FILE.toString(),
                Toast.LENGTH_SHORT).show();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });

    }
    public void stopPlayback(){

    }


    }


