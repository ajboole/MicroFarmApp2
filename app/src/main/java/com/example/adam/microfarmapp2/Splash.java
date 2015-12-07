/**
 * Created by Adam on 11/21/2015.
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ImageView;

/**
 * This class is a splash screen which is called when the app first runs.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //this downloads up to date data from the server every time the app first starts.
        //We can comment this out if the server is non functional
        //new Downloader().execute("");

        //Animations for splash screen
        //uses splash.xml in layout
        //uses rotate.xml in anim
        final ImageView iv = (ImageView) findViewById (R.id.imageAnim);
        final ImageView iv2 = (ImageView) findViewById (R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        //sets animation methods, and this is where the screen gets changed to the home screen
        //when the animation is finished
        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv2.startAnimation(an2);
                iv.startAnimation(an2);
                finish();
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }

        });


    }


}
