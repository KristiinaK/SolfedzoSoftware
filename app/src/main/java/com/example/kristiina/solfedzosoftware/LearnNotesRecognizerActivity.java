package com.example.kristiina.solfedzosoftware;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jtransforms.fft.DoubleFFT_1D;

public class LearnNotesRecognizerActivity extends AppCompatActivity {

    public static final String PREFERENCES = "Preferences";
    Boolean isRecording;
    TextView answerTW;
    String settings;
    Button recordBtn;
    Button stopBtn;
    Button playBtn;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_notes_recognizer);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        answerTW = (TextView) findViewById(R.id.answerTW);
        recordBtn= (Button) findViewById(R.id.recordBtn);
        stopBtn= (Button) findViewById(R.id.stopBtn);
        playBtn= (Button) findViewById(R.id.playBtn);
        SharedPreferences preferences  = getSharedPreferences(PREFERENCES,0);
        settings= preferences.getString("settings","");

        stopBtn.setEnabled(false);
        playBtn.setEnabled(false);

    }

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private boolean permissionToRecordAccepted = false;
    private boolean permissionToWriteExternalStorage = false;

    private String [] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                permissionToWriteExternalStorage  = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted || !permissionToWriteExternalStorage) finish();


    }

    public void onClick_start(final View view){

        recordBtn.setEnabled(false);
        stopBtn.setEnabled(true);
        playBtn.setEnabled(false);
        answerTW.setText("SALVESTAN...");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isRecording=true;
                startRecord();
            }
        });
        thread.start();
    }

    public void onClick_stop(final View view){
        isRecording=false;
        recordBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        playBtn.setEnabled(true);
    }

    public void onClick_play(final View view){

        recordBtn.setEnabled(false);
        stopBtn.setEnabled(false);
        playBtn.setEnabled(false);
        answerTW.setText("ARVUTAN...");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                playRecord();

            }
        });
        thread.start();

    }




    private void startRecord(){
        File file = new File(Environment.getExternalStorageDirectory(), "recordedFile.pcm");

        try {
            file.createNewFile();
        } catch (IOException e) {
            Log.d("record:", "couldnt make new file");
            e.printStackTrace();
        }

        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int bufferSize = AudioRecord.getMinBufferSize(
                    44100,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT);

            AudioRecord audioRecord = new AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    44100,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize);

            audioRecord.startRecording();

            short[] audioData = new short[bufferSize];

            while(isRecording){
                int numberOfShort = audioRecord.read(audioData, 0, bufferSize);
                for(int i = 0; i < numberOfShort; i++){
                    try {
                        dataOutputStream.writeShort(audioData[i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            audioRecord.stop();
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                answerTW.setText("");


            }
        });

    }

    private void playRecord(){


        File file = new File(Environment.getExternalStorageDirectory(), "recordedFile.pcm");

        int shortSizeInBytes = Short.SIZE/Byte.SIZE;
        int bufferSizeInBytes = (int)(file.length()/shortSizeInBytes);
        Log.d("VIVZ", "short size "+ Short.SIZE);
        Log.d("VIVZ", "byte size "+ Byte.SIZE);
        Log.d("VIVZ", "file size in bytes "+ file.length());


        short[] dataForPlayback = new short[bufferSizeInBytes];
        double[] dataForFFT = new double[bufferSizeInBytes];
        int frequency = 0;
        String result_note="";
        try {

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

            int i = 0;
            while(dataInputStream.available() > 0){
                dataForPlayback[i] = dataInputStream.readShort();
                dataForFFT[i] = dataForPlayback[i] / 100.0;
                i++;
            }


            dataInputStream.close();

            DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(dataForFFT.length);
            double[] fft = new double[dataForFFT.length * 2];
            System.arraycopy(dataForFFT, 0, fft, 0, dataForFFT.length);
            doubleFFT_1D.complexForward(fft);

            double [] magnitude=new double[dataForPlayback.length/2];

            for(int j=0; j< (dataForPlayback.length/2-1);j++){
                double re = fft[2*j];
                double im = fft [2*j+1];
                magnitude[j] =Math.sqrt (re*re + im*im);
            }

            double max_mag= Double.NEGATIVE_INFINITY;
            int max_ind= -1;
            for(int k=0; k< (dataForPlayback.length/2-1);k++){
                if(magnitude[k]>max_mag){

                    max_mag=magnitude[k];
                    max_ind=k;
                }
            }


             frequency = max_ind * 44100 / dataForPlayback.length;


            if (settings.equals("C, D, E, F, G, A, H")) {
                if (522 <= frequency && frequency <= 524 || 1045 <= frequency && frequency <= 1048 || 260 <= frequency && frequency <= 263 || 2092 <= frequency && frequency <= 2094 ) {
                    result_note="C";
                }else if(586 <= frequency && frequency <= 588 || 1173 <= frequency && frequency <= 1176 || 292 <= frequency && frequency <= 295){
                    result_note="D";
                }else if(658 <= frequency && frequency <= 660 || 1317 <= frequency && frequency <= 1320 || 328 <= frequency && frequency <= 331){
                    result_note="E";
                }else if(697 <= frequency && frequency <= 699 || 1395 <= frequency && frequency <= 1398 || 348 <= frequency && frequency <= 351){
                    result_note="F";
                }else if(782 <= frequency && frequency <= 785 || 1567 <= frequency && frequency <= 1569 || 391 <= frequency && frequency <= 393){
                    result_note="G";
                }else if(879 <= frequency && frequency <= 881 || 1759 <= frequency && frequency <= 1761 || 439 <= frequency && frequency <= 441){
                    result_note="A";
                }else if(986 <= frequency && frequency <= 989 || 1974 <= frequency && frequency <= 1977 || 492 <= frequency && frequency <= 495){
                    result_note="H";
                }

            }else if(settings.equals("C, D, E, F, G, A, B")){

                if (522 <= frequency && frequency <= 524 || 1045 <= frequency && frequency <= 1048 || 260 <= frequency && frequency <= 263 || 2092 <= frequency && frequency <= 2094 ) {
                    result_note="C";
                }else if(586 <= frequency && frequency <= 588 || 1173 <= frequency && frequency <= 1176 || 292 <= frequency && frequency <= 295){
                    result_note="D";
                }else if(658 <= frequency && frequency <= 660 || 1317 <= frequency && frequency <= 1320 || 328 <= frequency && frequency <= 331){
                    result_note="E";
                }else if(697 <= frequency && frequency <= 699 || 1395 <= frequency && frequency <= 1398 || 348 <= frequency && frequency <= 351){
                    result_note="F";
                }else if(782 <= frequency && frequency <= 785 || 1567 <= frequency && frequency <= 1569 || 391 <= frequency && frequency <= 393){
                    result_note="G";
                }else if(879 <= frequency && frequency <= 881 || 1759 <= frequency && frequency <= 1761 || 439 <= frequency && frequency <= 441){
                    result_note="A";
                }else if(986 <= frequency && frequency <= 989 || 1974 <= frequency && frequency <= 1977 || 492 <= frequency && frequency <= 495){
                    result_note="B";
                }

            }else if (settings.equals("DO, RE, MI, FA, SOL, LA, SI")) {
                if (522 <= frequency && frequency <= 524 || 1045 <= frequency && frequency <= 1048 || 260 <= frequency && frequency <= 263 || 2092 <= frequency && frequency <= 2094 ) {
                    result_note="DO";
                }else if(586 <= frequency && frequency <= 588 || 1173 <= frequency && frequency <= 1176 || 292 <= frequency && frequency <= 295){
                    result_note="RE";
                }else if(658 <= frequency && frequency <= 660 || 1317 <= frequency && frequency <= 1320 || 328 <= frequency && frequency <= 331){
                    result_note="MI";
                }else if(697 <= frequency && frequency <= 699 || 1395 <= frequency && frequency <= 1398 || 348 <= frequency && frequency <= 351){
                    result_note="FA";
                }else if(782 <= frequency && frequency <= 785 || 1567 <= frequency && frequency <= 1569 || 391 <= frequency && frequency <= 393){
                    result_note="SOL";
                }else if(879 <= frequency && frequency <= 881 || 1759 <= frequency && frequency <= 1761 || 439 <= frequency && frequency <= 441){
                    result_note="LA";
                }else if(986 <= frequency && frequency <= 989 || 1974 <= frequency && frequency <= 1977 || 492 <= frequency && frequency <= 495){
                    result_note="SI";
                }
            } else{
                if (522 <= frequency && frequency <= 524 || 1045 <= frequency && frequency <= 1048 || 260 <= frequency && frequency <= 263 || 2092 <= frequency && frequency <= 2094 ) {
                    result_note="C";
                }else if(586 <= frequency && frequency <= 588 || 1173 <= frequency && frequency <= 1176 || 292 <= frequency && frequency <= 295){
                    result_note="D";
                }else if(658 <= frequency && frequency <= 660 || 1317 <= frequency && frequency <= 1320 || 328 <= frequency && frequency <= 331){
                    result_note="E";
                }else if(697 <= frequency && frequency <= 699 || 1395 <= frequency && frequency <= 1398 || 348 <= frequency && frequency <= 351){
                    result_note="F";
                }else if(782 <= frequency && frequency <= 785 || 1567 <= frequency && frequency <= 1569 || 391 <= frequency && frequency <= 393){
                    result_note="G";
                }else if(879 <= frequency && frequency <= 881 || 1759 <= frequency && frequency <= 1761 || 439 <= frequency && frequency <= 441){
                    result_note="A";
                }else if(986 <= frequency && frequency <= 989 || 1974 <= frequency && frequency <= 1977 || 492 <= frequency && frequency <= 495){
                    result_note="H";
                }
            }


            AudioTrack audioTrack = new AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    44100,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSizeInBytes,
                    AudioTrack.MODE_STREAM);

            audioTrack.play();
            audioTrack.write(dataForPlayback, 0, bufferSizeInBytes);





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int finalFrequency = frequency;
        final String finalResult_note = result_note;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(finalResult_note.equals("")){
                    answerTW.setText(""+ finalFrequency + (" Hz \n") + "EI SUUTNUD NOOTI TUVASTADA");
                }else{
                    answerTW.setText(""+ finalFrequency + (" Hz \n") + "NOOT: "+ finalResult_note);

                }

                recordBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                playBtn.setEnabled(true);
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        Runtime.getRuntime().gc();

    }

}