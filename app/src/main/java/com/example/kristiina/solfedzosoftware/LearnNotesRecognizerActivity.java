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
import android.Manifest;
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

//Permissions:
//https://developer.android.com/guide/topics/media/mediarecorder.html
//Audiorecording and playback
//http://android-er.blogspot.com.ee/2014/04/audiorecord-and-audiotrack-and-to.html
//Shared preferences:
//https://developer.android.com/training/basics/data-storage/shared-preferences.html
//FFT:
//https://gist.github.com/jongukim/4037243
//http://stackoverflow.com/a/7675171
public class LearnNotesRecognizerActivity extends AppCompatActivity {

    public static final String PREFERENCES = "Preferences";
    Boolean isRecording;
    TextView answerTW;
    String settings;
    Button recordBtn;
    Button stopBtn;
    Button playBtn;


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

    //Permissions:
    //https://developer.android.com/guide/topics/media/mediarecorder.html
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private boolean permissionToWriteExternalStorage = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    //https://developer.android.com/guide/topics/media/mediarecorder.html
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
    //Audiorecording and playback
    //http://android-er.blogspot.com.ee/2014/04/audiorecord-and-audiotrack-and-to.html
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
    //Audiorecording and playback
    //http://android-er.blogspot.com.ee/2014/04/audiorecord-and-audiotrack-and-to.html
    public void onClick_stop(final View view){
        isRecording=false;
        recordBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        playBtn.setEnabled(true);
    }
    //Audiorecording and playback
    //http://android-er.blogspot.com.ee/2014/04/audiorecord-and-audiotrack-and-to.html
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




    //Audiorecording and playback
    //http://android-er.blogspot.com.ee/2014/04/audiorecord-and-audiotrack-and-to.html
    private void startRecord(){
        File file = new File(Environment.getExternalStorageDirectory(), "recordedFile.pcm");

        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)));


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

            while (isRecording) {
                int numberOfShort = audioRecord.read(audioData, 0, bufferSize);
                for (int i = 0; i < numberOfShort; i++) {
                    try {
                        dataOutputStream.writeShort(audioData[i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            audioRecord.stop();

            dataOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                answerTW.setText("");
            }
        });
    }

    //Audiorecording and playback
    //http://android-er.blogspot.com.ee/2014/04/audiorecord-and-audiotrack-and-to.html
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

            //https://gist.github.com/jongukim/4037243
            DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(dataForFFT.length);
            double[] fft = new double[dataForFFT.length * 2];
            System.arraycopy(dataForFFT, 0, fft, 0, dataForFFT.length);
            doubleFFT_1D.complexForward(fft);

            //http://stackoverflow.com/a/7675171
            double [] magnitude=new double[dataForFFT.length/2];
            for(int j=0; j< (dataForFFT.length/2-1);j++){
                double re = fft[2*j];
                double im = fft [2*j+1];
                magnitude[j] =Math.sqrt (re*re + im*im);
            }
            double max_mag= Double.NEGATIVE_INFINITY;
            int max_ind= -1;
            for(int k=0; k< (dataForFFT.length/2-1);k++){
                if(magnitude[k]>max_mag){
                    max_mag=magnitude[k];
                    max_ind=k;
                }
            }
            frequency = max_ind * 44100 / dataForFFT.length;


            if (settings.equals("C, D, E, F, G, A, H")) {
                if (127 <= frequency && frequency <= 138.591 || 255.285 <= frequency && frequency <= 277.183 || 508.568 <= frequency && frequency <= 554.365 || 1017.1336 <= frequency && frequency <= 1108.73 || 2034.266 <= frequency && frequency <= 2217.46) {
                    result_note="C";
                }else if(138.592 <= frequency && frequency <= 155.563 || 277.184 <= frequency && frequency <= 311.127 || 554.366 <= frequency && frequency <= 622.254|| 1108.74 <= frequency && frequency <= 1244.51|| 2217.47 <= frequency && frequency <= 2489.02){
                    result_note="D";
                }else if(155.564 <= frequency && frequency <= 169.714 || 311.128 <= frequency && frequency <= 339.428 || 622.255 <= frequency && frequency <= 678.855 || 1244.52 <= frequency && frequency <= 1357.71|| 2489.03 <= frequency && frequency <= 2715.425){
                    result_note="E";
                }else if(169.714 <= frequency && frequency <= 184.997 || 339.429 <= frequency && frequency <= 369.994 || 678.886 <= frequency && frequency <= 739.989 || 1357.72 <= frequency && frequency <= 1479.98|| 2715.426 <= frequency && frequency <= 2959.96){
                    result_note="F";
                }else if(184.998 <= frequency && frequency <= 207.652 || 369.995 <= frequency && frequency <= 415.305 || 739.990 <= frequency && frequency <= 830.609 || 1479.99 <= frequency && frequency <= 1661.22|| 2959.97 <= frequency && frequency <= 3322.44){
                    result_note="G";
                }else if(207.653 <= frequency && frequency <= 233.082 || 415.306 <= frequency && frequency <= 466.164 || 830.610 <= frequency && frequency <= 932.328 || 1611.23 <= frequency && frequency <= 1864.66|| 3322.45 <= frequency && frequency <= 3729.31){
                    result_note="A";
                }else if(233.083 <= frequency && frequency <= 255.284 || 466.165 <= frequency && frequency <= 508.567 || 932.329 <= frequency && frequency <= 1017.1335 || 1864.67 <= frequency && frequency <= 2034.265|| 3729.32 <= frequency && frequency <= 4068.54){
                    result_note="H";
                }


            }else if(settings.equals("C, D, E, F, G, A, B")){

                if (127 <= frequency && frequency <= 138.591 || 255.285 <= frequency && frequency <= 277.183 || 508.568 <= frequency && frequency <= 554.365 || 1017.1336 <= frequency && frequency <= 1108.73 || 2034.266 <= frequency && frequency <= 2217.46) {
                    result_note="C";
                }else if(138.592 <= frequency && frequency <= 155.563 || 277.184 <= frequency && frequency <= 311.127 || 554.366 <= frequency && frequency <= 622.254|| 1108.74 <= frequency && frequency <= 1244.51|| 2217.47 <= frequency && frequency <= 2489.02){
                    result_note="D";
                }else if(155.564 <= frequency && frequency <= 169.714 || 311.128 <= frequency && frequency <= 339.428 || 622.255 <= frequency && frequency <= 678.855 || 1244.52 <= frequency && frequency <= 1357.71|| 2489.03 <= frequency && frequency <= 2715.425){
                    result_note="E";
                }else if(169.714 <= frequency && frequency <= 184.997 || 339.429 <= frequency && frequency <= 369.994 || 678.886 <= frequency && frequency <= 739.989 || 1357.72 <= frequency && frequency <= 1479.98|| 2715.426 <= frequency && frequency <= 2959.96){
                    result_note="F";
                }else if(184.998 <= frequency && frequency <= 207.652 || 369.995 <= frequency && frequency <= 415.305 || 739.990 <= frequency && frequency <= 830.609 || 1479.99 <= frequency && frequency <= 1661.22|| 2959.97 <= frequency && frequency <= 3322.44){
                    result_note="G";
                }else if(207.653 <= frequency && frequency <= 233.082 || 415.306 <= frequency && frequency <= 466.164 || 830.610 <= frequency && frequency <= 932.328 || 1611.23 <= frequency && frequency <= 1864.66|| 3322.45 <= frequency && frequency <= 3729.31){
                    result_note="A";
                }else if(233.083 <= frequency && frequency <= 255.284 || 466.165 <= frequency && frequency <= 508.567 || 932.329 <= frequency && frequency <= 1017.1335 || 1864.67 <= frequency && frequency <= 2034.265|| 3729.32 <= frequency && frequency <= 4068.54){
                    result_note="B";
                }

            }else if (settings.equals("DO, RE, MI, FA, SOL, LA, SI")) {
                if (127 <= frequency && frequency <= 138.591 || 255.285 <= frequency && frequency <= 277.183 || 508.568 <= frequency && frequency <= 554.365 || 1017.1336 <= frequency && frequency <= 1108.73 || 2034.266 <= frequency && frequency <= 2217.46) {
                    result_note="DO";
                }else if(138.592 <= frequency && frequency <= 155.563 || 277.184 <= frequency && frequency <= 311.127 || 554.366 <= frequency && frequency <= 622.254|| 1108.74 <= frequency && frequency <= 1244.51|| 2217.47 <= frequency && frequency <= 2489.02){
                    result_note="RE";
                }else if(155.564 <= frequency && frequency <= 169.714 || 311.128 <= frequency && frequency <= 339.428 || 622.255 <= frequency && frequency <= 678.855 || 1244.52 <= frequency && frequency <= 1357.71|| 2489.03 <= frequency && frequency <= 2715.425){
                    result_note="MI";
                }else if(169.714 <= frequency && frequency <= 184.997 || 339.429 <= frequency && frequency <= 369.994 || 678.886 <= frequency && frequency <= 739.989 || 1357.72 <= frequency && frequency <= 1479.98|| 2715.426 <= frequency && frequency <= 2959.96){
                    result_note="FA";
                }else if(184.998 <= frequency && frequency <= 207.652 || 369.995 <= frequency && frequency <= 415.305 || 739.990 <= frequency && frequency <= 830.609 || 1479.99 <= frequency && frequency <= 1661.22|| 2959.97 <= frequency && frequency <= 3322.44){
                    result_note="SOL";
                }else if(207.653 <= frequency && frequency <= 233.082 || 415.306 <= frequency && frequency <= 466.164 || 830.610 <= frequency && frequency <= 932.328 || 1611.23 <= frequency && frequency <= 1864.66|| 3322.45 <= frequency && frequency <= 3729.31){
                    result_note="LA";
                }else if(233.083 <= frequency && frequency <= 255.284 || 466.165 <= frequency && frequency <= 508.567 || 932.329 <= frequency && frequency <= 1017.1335 || 1864.67 <= frequency && frequency <= 2034.265|| 3729.32 <= frequency && frequency <= 4068.54){
                    result_note="SI";
                }
            } else{
                if (127 <= frequency && frequency <= 138.591 || 255.285 <= frequency && frequency <= 277.183 || 508.568 <= frequency && frequency <= 554.365 || 1017.1336 <= frequency && frequency <= 1108.73 || 2034.266 <= frequency && frequency <= 2217.46) {
                    result_note="C";
                }else if(138.592 <= frequency && frequency <= 155.563 || 277.184 <= frequency && frequency <= 311.127 || 554.366 <= frequency && frequency <= 622.254|| 1108.74 <= frequency && frequency <= 1244.51|| 2217.47 <= frequency && frequency <= 2489.02){
                    result_note="D";
                }else if(155.564 <= frequency && frequency <= 169.714 || 311.128 <= frequency && frequency <= 339.428 || 622.255 <= frequency && frequency <= 678.855 || 1244.52 <= frequency && frequency <= 1357.71|| 2489.03 <= frequency && frequency <= 2715.425){
                    result_note="E";
                }else if(169.714 <= frequency && frequency <= 184.997 || 339.429 <= frequency && frequency <= 369.994 || 678.886 <= frequency && frequency <= 739.989 || 1357.72 <= frequency && frequency <= 1479.98|| 2715.426 <= frequency && frequency <= 2959.96){
                    result_note="F";
                }else if(184.998 <= frequency && frequency <= 207.652 || 369.995 <= frequency && frequency <= 415.305 || 739.990 <= frequency && frequency <= 830.609 || 1479.99 <= frequency && frequency <= 1661.22|| 2959.97 <= frequency && frequency <= 3322.44){
                    result_note="G";
                }else if(207.653 <= frequency && frequency <= 233.082 || 415.306 <= frequency && frequency <= 466.164 || 830.610 <= frequency && frequency <= 932.328 || 1611.23 <= frequency && frequency <= 1864.66|| 3322.45 <= frequency && frequency <= 3729.31){
                    result_note="A";
                }else if(233.083 <= frequency && frequency <= 255.284 || 466.165 <= frequency && frequency <= 508.567 || 932.329 <= frequency && frequency <= 1017.1335 || 1864.67 <= frequency && frequency <= 2034.265|| 3729.32 <= frequency && frequency <= 4068.54){
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
        Runtime.getRuntime().gc();
        finish();
    }

}