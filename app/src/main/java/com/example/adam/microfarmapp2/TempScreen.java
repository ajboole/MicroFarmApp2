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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//This class is for the Temperature Settings Screen

public class TempScreen extends AppCompatActivity {

    //Uses settingsList and regexSettingsList as a temporary storage for CSV file values.
    //The program first reads the CSV into these every time the screen is created.
    ArrayList<Integer> settingsList = new ArrayList<>();
    String regexSettingsList;
    int isCelsius;

    //Screen Creation Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_screen);

        //readLog method called
        readLog();
        isCelsius = settingsList.get(9);

        //Text view for toggling between F and C
        TextView degrees = (TextView)findViewById(R.id.degreeF);
        TextView degrees2 = (TextView)findViewById(R.id.degreeF2);

        //checks if celsius is true and changes accordingly
        if (isCelsius == 1){

            degrees.setText(" ° C");
            degrees2.setText(" ° C");

        }

        //sets the default values to the widgets
        final NumberPicker temp_number = (NumberPicker) findViewById(R.id.tempNumberPicker);
        temp_number.setValue((int) settingsList.get(3));

        final NumberPicker temp_buffer = (NumberPicker) findViewById(R.id.tempBufferNumberPicker);
        temp_buffer.setValue((int) settingsList.get(4));


    }

    //Click Listener, first saves widget settings to Local CSV, then calls the Uploader to give them to the server.
    public void onClickSetTemp(View view) {

        this.saveLogOnClick(view);
        //new Uploader().execute("");

    }

    //this method sets values from widgets to settingsList, then the log.csv
    public void saveLogOnClick(View view){

        final NumberPicker temp_number = (NumberPicker) findViewById(R.id.tempNumberPicker);
        final NumberPicker temp_buffer = (NumberPicker) findViewById(R.id.tempBufferNumberPicker);

        settingsList.set(3, temp_number.getValue());
        settingsList.set(4, temp_buffer.getValue());

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
