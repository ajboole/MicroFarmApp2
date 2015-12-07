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

public class WaterScreen extends AppCompatActivity {

    ArrayList<Integer> settingsList = new ArrayList<>();
    String regexSettingsList;
    private int days;
    private int hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_screen);

        readLog();
        days = (int)settingsList.get(5)/24;
        hours = (int)settingsList.get(5)%24;

        final NumberPicker water_days = (NumberPicker) findViewById(R.id.waterdayNumberPicker);
        water_days.setValue(days);

        final NumberPicker water_hours = (NumberPicker) findViewById(R.id.waterhourNumberPicker);
        water_hours.setValue(hours);

        final NumberPicker water_intensity = (NumberPicker) findViewById(R.id.waterIntensityNumberPicker);
        water_intensity.setValue((int) settingsList.get(6));
    }

    public void onClickSetWater(View view) {

        this.saveLogOnClick(view);

    }

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

        Toast.makeText(getBaseContext(), regexSettingsList.toString(), Toast.LENGTH_SHORT).show();

    }

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
