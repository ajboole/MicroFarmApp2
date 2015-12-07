/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/
package com.example.adam.microfarmapp2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//This class is for the Water Settings Screen
public class WaterScreen extends AppCompatActivity {

    //Uses settingsList and regexSettingsList as a temporary storage for CSV file values.
    //The program first reads the CSV into these every time the screen is created.
    ArrayList<Integer> settingsList = new ArrayList<>();
    String regexSettingsList;

    //The water settings reads the csv file as only hours, we need to separate these into days and hours.
    private int days;
    private int hours;

    //Screen Creation Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_screen);

        //readLog method called
        readLog();

        //this is to separate the hours into both days and hours
        days = (int)settingsList.get(5)/24;
        hours = (int)settingsList.get(5)%24;

        //sets the default values to the widgets
        final NumberPicker water_days = (NumberPicker) findViewById(R.id.waterdayNumberPicker);
        water_days.setValue(days);

        final NumberPicker water_hours = (NumberPicker) findViewById(R.id.waterhourNumberPicker);
        water_hours.setValue(hours);

        final NumberPicker water_intensity = (NumberPicker) findViewById(R.id.waterIntensityNumberPicker);
        water_intensity.setValue((int) settingsList.get(6));
    }

    //Click Listener, first saves widget settings to Local CSV, then calls the Uploader to give them to the server.
    public void onClickSetWater(View view) {

        this.saveLogOnClick(view);
        new Uploader().execute("");

    }

    //this method sets values from widgets to settingsList, then the log.csv
    public void saveLogOnClick(View view){

        final NumberPicker water_days = (NumberPicker) findViewById(R.id.waterdayNumberPicker);
        final NumberPicker water_hours = (NumberPicker) findViewById(R.id.waterhourNumberPicker);
        final NumberPicker water_intensity = (NumberPicker) findViewById(R.id.waterIntensityNumberPicker);
        int combined = ((water_days.getValue()) * 24) + water_hours.getValue();

        //NOTE: CHANGE TO setHour AND setMinute for API 23
        settingsList.set(5, combined);
        settingsList.set(6, water_intensity.getValue());

        String FILENAME = "log.csv";

        try {

            FileOutputStream out = openFileOutput( FILENAME, Context.MODE_PRIVATE);

            regexSettingsList = settingsList.toString();
            regexSettingsList = regexSettingsList.replaceAll("\\s+","").replaceAll("\\[", "").replaceAll("\\]","");

            out.write(regexSettingsList.toString().getBytes() );
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(getBaseContext(), "Changes Set", Toast.LENGTH_SHORT).show();

    }

    //this method reads the local log.csv to the Array List settingsList
    private void readLog(){

        String FILENAME = "log.csv";
        FileInputStream inputStream = null;
        String temp;
        String a[];

        try {

            inputStream = openFileInput(FILENAME);
            byte[] reader = new byte[inputStream.available()];
            while (inputStream.read(reader) != -1) {
            }

            Scanner s = new Scanner(new String(reader));
            s.useDelimiter("\\n");

            while (s.hasNext()) {

                temp = s.next();
                a = temp.split(",");
                for (int i = 0; i < a.length; i++) {
                    settingsList.add(Integer.parseInt(a[i]));
                }


            }

            s.close();

        } catch (Exception e) {
            Log.e("Chart", e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("Chart", e.getMessage());
                }
            }
        }

    }

}
