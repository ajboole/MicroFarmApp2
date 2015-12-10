package com.example.adam.microfarmapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Adam on 12/9/2015.
 */
public class Drawing extends View {

    public Drawing(Context context){

        super(context);

    }



    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Rect ourRect = new Rect();
        ourRect.set(0,90,90,0);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(ourRect, blue);

    }

}
