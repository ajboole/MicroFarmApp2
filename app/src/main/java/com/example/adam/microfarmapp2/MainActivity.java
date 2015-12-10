/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import java.util.logging.Handler;

import android.content.res.AssetManager;


/**
 *
 * Main Activity also known as the Home Screen where we can access all
 * the elements (light, temp, water, humidity, stats, history, settings)
 *
 * It is important that any screen you could need for the app can be accessed by this one screen
 * This keeps the user from having to search through multiple screens to find what they are looking for.
 *
 * This is also known as the home screen.
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

        //TextView light_text = (TextView)findViewById(R.id.lighttext);
        //TextView temp_text = (TextView)findViewById(R.id.temptext);
        //TextView humidity_text = (TextView)findViewById(R.id.humiditytext);

        //Small values for testing if it works on the home screen, this will be commented out later
        //values tested are for lights, temp, and humidity

        //light_text.setText(settingsList.get(0).toString());
        //temp_text.setText(settingsList.get(3).toString());
        //humidity_text.setText(settingsList.get(7).toString());

        ImageView imgView = (ImageView)findViewById(R.id.imgView);
        WindowManager wManager = getWindowManager();
        Display display = wManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screen_width=metrics.widthPixels;
        int screen_height=metrics.heightPixels;

        imgView.setImageBitmap(drawLines(screen_width, screen_height));



        Log.i(TAG, "onCreate");

    }

    private Bitmap drawLines(int bgWidth, int bgHeight){

        int lightbarHeight = bgHeight - (settingsList.get(0) * 40);


        Paint lightbar = new Paint();
        lightbar.setColor(Color.parseColor("#FFDE94"));
        //lightbar.setAlpha(255);

        Paint tempbar = new Paint();
        tempbar.setColor(Color.parseColor("#E64C3C"));
        //tempbar.setAlpha(255);

        Paint humiditybar = new Paint();
        humiditybar.setColor(Color.parseColor("#3B97D3"));
        //humiditybar.setAlpha(255);

        Bitmap bitmapBackground= Bitmap.createBitmap(bgWidth,bgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBackground);

        lightbar.setStrokeWidth(1);
        canvas.drawLine(5, lightbarHeight, 5, bgHeight - 20, lightbar);

        tempbar.setStrokeWidth(1);
        canvas.drawLine(20, (float)(bgHeight - (settingsList.get(3) * 9.6)), 20, bgHeight - 20, tempbar);

        humiditybar.setStrokeWidth(1);
        canvas.drawLine(33, (float)(bgHeight - (settingsList.get(7) * 9.6)), 33, bgHeight - 20, humiditybar);

        return bitmapBackground;


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

    //Methods for understanding where errors occur in the App
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

    //Reads the data from the local log.csv to an Array List settingsList
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
