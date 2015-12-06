package com.example.adam.microfarmapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LightScreen extends AppCompatActivity {


    ArrayList<Integer> settingsList = new ArrayList<Integer>();
    String regexSettingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_screen);

        readLog();

        final NumberPicker light_cycle_time = (NumberPicker) findViewById(R.id.lightNumberPicker);
        light_cycle_time.setValue((int)settingsList.get(0));

        final TimePicker light_starting_time = (TimePicker) findViewById(R.id.lightTimePicker);
        light_starting_time.setHour((int)settingsList.get(1));
        light_starting_time.setMinute((int)settingsList.get(2));
    }

    public void onClickSetLights(View view) {

        this.saveLogOnClick(view);
        //this.readLog();

    }

    public void saveLogOnClick(View view){

        final NumberPicker light_cycle_time = (NumberPicker) findViewById(R.id.lightNumberPicker);
        final TimePicker light_starting_time = (TimePicker) findViewById(R.id.lightTimePicker);

        settingsList.set(0, light_cycle_time.getValue());
        settingsList.set(1, light_starting_time.getHour());
        settingsList.set(2, light_starting_time.getMinute());

        String FILENAME = "log.csv";
        //String entry = "" + light_starting_time.getHour() + "," +
        //light_starting_time.getMinute() + "\n";

        try {

            FileOutputStream out = openFileOutput( FILENAME, Context.MODE_PRIVATE);

            regexSettingsList = settingsList.toString();
            regexSettingsList = regexSettingsList.replaceAll("\\s+","").replaceAll("\\[", "").replaceAll("\\]","");

            out.write(regexSettingsList.toString().getBytes() );
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(getBaseContext(), regexSettingsList.toString(), Toast.LENGTH_SHORT).show();

    }

    private void readLog(){

        String FILENAME = "log.csv";
        FileInputStream inputStream = null;
        String temp;
        String a[];

        try{
            inputStream = openFileInput( FILENAME );
            byte[] reader = new byte[ inputStream.available() ];
            while( inputStream.read(reader) != -1){}

            Scanner s = new Scanner(new String(reader));
            s.useDelimiter("\\n");

            while(s.hasNext()){

                temp = s.next();
                a = temp.split(",");
                for(int i = 0; i < a.length; i++){
                    settingsList.add(Integer.parseInt(a[i]));
                }


            }

            s.close();

        }catch (Exception e){
            Log.e("Chart", e.getMessage());
        }finally{
            if( inputStream != null){
                try{
                    inputStream.close();
                }catch(IOException e){
                    Log.e("Chart", e.getMessage());
                }
            }
        }


    }

}
