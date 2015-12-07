/**
 * Adam Boole
 * MicroFarm App, to control a raspberry pi powered micro climate box for growing plants.
 * Project created for Human Computer Interaction
 **/
package com.example.adam.microfarmapp2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.NumberPicker;

//This is an extension of the number picker so we can set the mins and maxes in the xml classes.
@TargetApi(Build.VERSION_CODES.HONEYCOMB)//For backward-compability
public class myNumberPicker extends NumberPicker {

    public myNumberPicker(Context context) {
        super(context);
    }

    public myNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributeSet(attrs);
    }

    public myNumberPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }
    private void processAttributeSet(AttributeSet attrs) {
        //This method reads the parameters given in the xml file and sets the properties according to it
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 0));
    }
}