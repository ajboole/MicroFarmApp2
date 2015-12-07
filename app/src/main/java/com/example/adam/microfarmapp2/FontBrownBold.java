/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/

package com.example.adam.microfarmapp2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Adam on 12/3/2015.
 * This class is for implementing the custom font Brown Bold
 */

public class FontBrownBold extends TextView {

    public FontBrownBold(Context context, AttributeSet attrs){

        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/brownbold.ttf"));

    }

}
