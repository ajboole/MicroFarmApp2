package com.example.adam.microfarmapp2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Adam on 12/3/2015.
 */
public class FontBrownBold extends TextView {

    public FontBrownBold(Context context, AttributeSet attrs){

        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/brownbold.ttf"));

    }

}
