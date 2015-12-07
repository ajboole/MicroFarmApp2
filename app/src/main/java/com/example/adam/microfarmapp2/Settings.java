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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//This is the Settings Screen for controlling interface settings, and downloading csv data from the server
//to overwrite the local log.csv
public class Settings extends AppCompatActivity {

    //Uses settingsList and regexSettingsList as a temporary storage for CSV file values.
    //The program first reads the CSV into these every time the screen is created.
    ArrayList<Integer> settingsList = new ArrayList<>();
    String regexSettingsList;
    int isCelsius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //tiny animated square code
        ImageView myImageView = (ImageView) findViewById(R.id.imageSquareSettings);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.tween);
        myImageView.startAnimation(myFadeInAnimation);

        readLog();

        RadioButton f_button = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton c_button = (RadioButton) findViewById(R.id.radioButton2);

        isCelsius = settingsList.get(9);

        if(isCelsius == 1){

            c_button.setChecked(true);

        }

        else if(isCelsius == 0){

            f_button.setChecked(true);

        }


    }

    //When the download button is clicked, the download method is called from the server
    public void onClickDownload(View view){

        new Downloader().execute("");
        Toast.makeText(getBaseContext(), "Download Initialized", Toast.LENGTH_SHORT).show();

    }

    //This method is called when one of the radio buttons is toggled
    //this will set the value in the log.csv as 1 if celsius is checked. Otherwise the value is a zero.
    public void onRadioButtonClicked(View view) {

        RadioButton f_button = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton c_button = (RadioButton) findViewById(R.id.radioButton2);

        //is farenheit checked?
        if (f_button.isChecked()){

            settingsList.set(9, 0);

        }

        //is celsius checked?
        else if (c_button.isChecked()){

            settingsList.set(9,1);

        }

        //sets the new data to the csv file
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

        new Uploader().execute("");

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
