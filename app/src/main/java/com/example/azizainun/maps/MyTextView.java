package com.example.azizainun.maps;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by aziza on 8/31/2017.
 */

public class MyTextView extends TextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*public MyTextView(Context context) {
        super(context);
//        init();
    }*/

    /*private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Raleway-Light.ttf");
            setTypeface(tf);
        }
    }*/

    private void init(Context context, AttributeSet attrs) {
        int typestyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

//        Typeface tf = Typeface.createFromAsset(context.getAssets(), "font/Raleway-Bold.ttf");
        Typeface typeface = selectStyle(context, typestyle);
        setTypeface(typeface);
    }

    private Typeface selectStyle(Context context, int typestyle) {
        switch (typestyle) {
            case Typeface.BOLD:
                return Typeface.createFromAsset(context.getAssets(), "font/Raleway-Bold.ttf");
            case Typeface.BOLD_ITALIC:
                return Typeface.createFromAsset(context.getAssets(), "font/Raleway-BoldItalic.ttf");
            case Typeface.ITALIC:
                return Typeface.createFromAsset(context.getAssets(), "font/Raleway-Italic.ttf");
            case Typeface.NORMAL:
                return Typeface.createFromAsset(context.getAssets(), "font/Raleway-Light.ttf");
            default:
                return Typeface.createFromAsset(context.getAssets(), "font/Raleway-Light.ttf");
        }
    }
}
