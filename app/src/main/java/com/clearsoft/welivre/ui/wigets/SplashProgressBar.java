package com.clearsoft.welivre.ui.wigets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by on 17.01.17.
 */

public class SplashProgressBar  extends ProgressBar{
    public SplashProgressBar(Context context) {
        super(context);
        init();
    }

    public SplashProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SplashProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getIndeterminateDrawable().setColorFilter(Color.parseColor("#555555"), PorterDuff.Mode.MULTIPLY);
    }

}
