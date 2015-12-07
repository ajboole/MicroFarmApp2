/**
 * Adam Boole, John Schutz, Parker Jacobsen, Michael Thomas
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import android.content.res.AssetManager;


/**
 *
 * Main Activity also known as the Home Screen where we can access all
 * the elements (light, temp, water, humidity, stats, history, settings)
 *
 * uses tween.xml from anim
 * and a bunch of xml files in the layout
 *
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mymessage";

    ArrayList<Integer> settingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tiny animated square code
        ImageView myImageView = (ImageView) findViewById(R.id.imageSquare);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.tween);
        myImageView.startAnimation(myFadeInAnimation);

        readLog();

        TextView light_text = (TextView)findViewById(R.id.lighttext);
        TextView temp_text = (TextView)findViewById(R.id.temptext);
        TextView humidity_text = (TextView)findViewById(R.id.humiditytext);

        light_text.setText(settingsList.get(0).toString());
        temp_text.setText(settingsList.get(3).toString());
        humidity_text.setText(settingsList.get(5).toString());

        //ImageView light_bar = (ImageView)findViewById(R.id.lightbar);

        //int lightbarheight = settingsList.get(0) * 40;

        //light_bar.getLayoutParams().height = lightbarheight;
        //light_bar.getLayoutParams().width = 5;
        //light_bar.requestLayout();

        Log.i(TAG, "onCreate");

    }

    //Method to go to LightScreen when Light button is pressed
    public void onClickLight(View view){

        Intent i = new Intent(this, LightScreen.class);
        startActivity(i);

    }

    //method to go to TempScreen when temp button is pressed
    public void onClickTemp(View view){

        Intent i = new Intent(this, TempScreen.class);
        startActivity(i);

    }

    //method to go to WaterScreen when water button is pressed
    public void onClickWater(View view){

        Intent i = new Intent(this, WaterScreen.class);
        startActivity(i);

    }

    //method to go to HumidityScreen when humidity button is pressed
    public void onClickHumidity(View view){

        Intent i = new Intent(this, HumidityScreen.class);
        startActivity(i);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
