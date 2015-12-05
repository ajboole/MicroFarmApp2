package com.example.adam.microfarmapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

//import au.com.bytecode.opencsv.CSVReader;

public class LightScreen extends AppCompatActivity {


    private NumberPicker light_cycle_time;
    private Button light_set_button;
    private String first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_screen);
        //setTime();
    }

    public void onClickSetLights(View view) {

        final TimePicker light_starting_time = (TimePicker) findViewById(R.id.lightTimePicker);

        //Toast.makeText(getBaseContext(), (int) light_starting_time.getHour() + " " +
                //(int) light_starting_time.getMinute() + " changes set.", Toast.LENGTH_SHORT).show();

        this.saveLogOnClick(view);

    }

    public void saveLogOnClick(View view){

        final TimePicker light_starting_time = (TimePicker) findViewById(R.id.lightTimePicker);

        String FILENAME = "log.csv";
        String entry = "" + light_starting_time.getHour() + "," +
        light_starting_time.getMinute() + "\n";

    try {

        //CSVReader reader = new CSVReader(new FileReader(FILENAME), ',');
        //List<String[]> csvBody = reader.readAll();
        //first = csvBody.get(0)[0];

        //Toast.makeText(getBaseContext(), this.first, Toast.LENGTH_SHORT).show();



        //FileOutputStream out = openFileOutput( FILENAME, Context.MODE_APPEND);
        //out.write( entry.getBytes() );
        //out.close();

    }catch (Exception e){
        e.printStackTrace();
    }


    }
}
