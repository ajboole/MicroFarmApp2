/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

//This is the Statistics Screen for getting data from a server csv file that has statistics for the box
public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //tiny animated square code
        ImageView myImageView = (ImageView) findViewById(R.id.imageSquareStatistics);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.tween);
        myImageView.startAnimation(myFadeInAnimation);
    }

}
