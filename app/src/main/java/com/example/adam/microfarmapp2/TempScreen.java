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
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TempScreen extends AppCompatActivity {

    ArrayList<Integer> settingsList = new ArrayList<>();
    String regexSettingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_screen);

        readLog();

        final NumberPicker temp_number = (NumberPicker) findViewById(R.id.tempNumberPicker);
        temp_number.setValue((int) settingsList.get(3));

        final NumberPicker temp_buffer = (NumberPicker) findViewById(R.id.tempBufferNumberPicker);
        temp_buffer.setValue((int) settingsList.get(4));


    }

    public void onClickSetTemp(View view) {

        this.saveLogOnClick(view);

    }

    public void saveLogOnClick(View view){

        final NumberPicker temp_number = (NumberPicker) findViewById(R.id.tempNumberPicker);
        final NumberPicker temp_buffer = (NumberPicker) findViewById(R.id.tempBufferNumberPicker);

        //NOTE: CHANGE TO setHour AND setMinute for API 23
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
