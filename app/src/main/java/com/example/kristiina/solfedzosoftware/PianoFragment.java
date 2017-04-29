package com.example.kristiina.solfedzosoftware;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PianoFragment extends Fragment{

    public static final String PREFERENCES = "Preferences";

    Button btn_C3, btn_D3, btn_E3, btn_F3,  btn_G3, btn_A3, btn_H3;
    Button btn_C4, btn_D4, btn_E4, btn_F4,  btn_G4, btn_A4, btn_H4;
    Button btn_C5, btn_D5, btn_E5, btn_F5,  btn_G5, btn_A5, btn_H5;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piano,container,false);

        btn_C3 = (Button) view.findViewById(R.id.btn_C3);
        btn_C4 = (Button) view.findViewById(R.id.btn_C4);
        btn_C5 = (Button) view.findViewById(R.id.btn_C5);
        btn_D3 = (Button) view.findViewById(R.id.btn_D3);
        btn_D4 = (Button) view.findViewById(R.id.btn_D4);
        btn_D5 = (Button) view.findViewById(R.id.btn_D5);
        btn_E3 = (Button) view.findViewById(R.id.brn_E3);
        btn_E4 = (Button) view.findViewById(R.id.btn_E4);
        btn_E5 = (Button) view.findViewById(R.id.brn_E5);
        btn_F3 = (Button) view.findViewById(R.id.btn_F3);
        btn_F4 = (Button) view.findViewById(R.id.btn_F4);
        btn_F5 = (Button) view.findViewById(R.id.btn_F5);
        btn_G3 = (Button) view.findViewById(R.id.btn_G3);
        btn_G4 = (Button) view.findViewById(R.id.btn_G4);
        btn_G5 = (Button) view.findViewById(R.id.btn_G5);
        btn_A3 = (Button) view.findViewById(R.id.btn_A3);
        btn_A4 = (Button) view.findViewById(R.id.btn_A4);
        btn_A5 = (Button) view.findViewById(R.id.btn_A5);
        btn_H3 = (Button) view.findViewById(R.id.btn_H3);
        btn_H4 = (Button) view.findViewById(R.id.btn_H4);
        btn_H5 = (Button) view.findViewById(R.id.btn_H5);


        SharedPreferences preferences  = this.getActivity().getSharedPreferences(PREFERENCES,0);

        String settings= preferences.getString("settings","");


        if (settings.equals("C, D, E, F, G, A, H")) {

            btn_C3.setText("C");
            btn_C4.setText("C");
            btn_C5.setText("C");
            btn_D3.setText("D");
            btn_D4.setText("D");
            btn_D5.setText("D");
            btn_E3.setText("E");
            btn_E4.setText("E");
            btn_E5.setText("E");
            btn_F3.setText("F");
            btn_F4.setText("F");
            btn_F5.setText("F");
            btn_G3.setText("G");
            btn_G4.setText("G");
            btn_G5.setText("G");
            btn_A3.setText("A");
            btn_A4.setText("A");
            btn_A5.setText("A");
            btn_H3.setText("H");
            btn_H4.setText("H");
            btn_H5.setText("H");

        }else if(settings.equals("C, D, E, F, G, A, B")){

            btn_C3.setText("C");
            btn_C4.setText("C");
            btn_C5.setText("C");
            btn_D3.setText("D");
            btn_D4.setText("D");
            btn_D5.setText("D");
            btn_E3.setText("E");
            btn_E4.setText("E");
            btn_E5.setText("E");
            btn_F3.setText("F");
            btn_F4.setText("F");
            btn_F5.setText("F");
            btn_G3.setText("G");
            btn_G4.setText("G");
            btn_G5.setText("G");
            btn_A3.setText("A");
            btn_A4.setText("A");
            btn_A5.setText("A");
            btn_H3.setText("B");
            btn_H4.setText("B");
            btn_H5.setText("B");

        } else if (settings.equals("DO, RE, MI, FA, SOL, LA, SI")) {
            btn_C3.setText("DO");
            btn_C4.setText("DO");
            btn_C5.setText("DO");
            btn_D3.setText("RE");
            btn_D4.setText("RE");
            btn_D5.setText("RE");
            btn_E3.setText("MI");
            btn_E4.setText("MI");
            btn_E5.setText("MI");
            btn_F3.setText("FA");
            btn_F4.setText("FA");
            btn_F5.setText("FA");
            btn_G3.setText("SOL");
            btn_G4.setText("SOL");
            btn_G5.setText("SOL");
            btn_A3.setText("LA");
            btn_A4.setText("LA");
            btn_A5.setText("LA");
            btn_H3.setText("SI");
            btn_H4.setText("SI");
            btn_H5.setText("SI");

        }else{

            btn_C3.setText("C");
            btn_C4.setText("C");
            btn_C5.setText("C");
            btn_D3.setText("D");
            btn_D4.setText("D");
            btn_D5.setText("D");
            btn_E3.setText("E");
            btn_E4.setText("E");
            btn_E5.setText("E");
            btn_F3.setText("F");
            btn_F4.setText("F");
            btn_F5.setText("F");
            btn_G3.setText("G");
            btn_G4.setText("G");
            btn_G5.setText("G");
            btn_A3.setText("A");
            btn_A4.setText("A");
            btn_A5.setText("A");
            btn_H3.setText("H");
            btn_H4.setText("H");
            btn_H5.setText("H");
        }




        return view;
    }


}
