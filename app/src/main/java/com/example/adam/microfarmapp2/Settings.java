/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

//This is the Settings Screen for controlling interface settings, and downloading csv data from the server
//to overwrite the local log.csv
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //tiny animated square code
        ImageView myImageView = (ImageView) findViewById(R.id.imageSquareSettings);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.tween);
        myImageView.startAnimation(myFadeInAnimation);
    }

    //When the download button is clicked, the download method is called from the server
    public void onClickDownload(View view){

        new Downloader().execute("");
        Toast.makeText(getBaseContext(), "Download Initialized", Toast.LENGTH_SHORT).show();

    }

}
