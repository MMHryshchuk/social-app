package com.clearsoft.welivre.ui.wigets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by on 01.12.16.
 */

public class WhiteProgressBar extends ProgressBar {

    private Context context;
    private AttributeSet attrs;
    private int defStyle;

    public WhiteProgressBar(Context context) {
        super(context);
        this.context=context;
        init();

    }

    public WhiteProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        this.attrs=attrs;
        init();
    }

    public WhiteProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        this.attrs=attrs;
        this.defStyle=defStyleAttr;
        init();
    }

    private void init() {
        getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
    }


}
