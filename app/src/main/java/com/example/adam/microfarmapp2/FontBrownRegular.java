/**
 * Adam Boole, John Schutz, Parker Jacobsen, Michael Thomas
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
 */
public class FontBrownRegular extends TextView {

    public FontBrownRegular(Context context, AttributeSet attrs){

        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/brownregular.ttf"));

    }

}