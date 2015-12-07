/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
**/

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

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**LightScreen class lets us interact with the Light Settings Screen
 * This class reads the data from the csv every time the screen is created
 */
public class LightScreen extends AppCompatActivity {

    //Uses settingsList and regexSettingsList as a temporary storage for CSV file values.
    //The program first reads the CSV into these every time the screen is created.
    ArrayList<Integer> settingsList = new ArrayList<>();
    String regexSettingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_screen);

        //Screen Creation Method
        readLog();

        //sets the default values to the widgets
        final NumberPicker light_cycle_time = (NumberPicker) findViewById(R.id.lightNumberPicker);
        light_cycle_time.setValue((int)settingsList.get(0));

        //NOTE: CHANGE TO setHour AND setMinute for API 23
        final TimePicker light_starting_time = (TimePicker) findViewById(R.id.lightTimePicker);
        light_starting_time.setCurrentHour((int) settingsList.get(1));
        light_starting_time.setCurrentMinute((int) settingsList.get(2));
    }

    //Click Listener, first saves widget settings to Local CSV, then calls the Uploader to give them to the server.
    public void onClickSetLights(View view) {

        this.saveLogOnClick(view);
        new Uploader().execute("");

    }

    //this method sets values from widgets to settingsList, then the log.csv
    public void saveLogOnClick(View view){

        final NumberPicker light_cycle_time = (NumberPicker) findViewById(R.id.lightNumberPicker);
        final TimePicker light_starting_time = (TimePicker) findViewById(R.id.lightTimePicker);

        //NOTE: CHANGE TO setHour AND setMinute for API 23
        settingsList.set(0, light_cycle_time.getValue());
        settingsList.set(1, light_starting_time.getCurrentHour());
        settingsList.set(2, light_starting_time.getCurrentMinute());

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
                //Uncomment this code for phones that don't yet have a CSV file.
                //FileOutputStream out = openFileOutput( FILENAME, Context.MODE_APPEND);
                //out.write("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0".getBytes());
                //out.close();

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
