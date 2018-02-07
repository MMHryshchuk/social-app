package com.clearsoft.welivre.ui.wigets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by on 01.12.16.
 */

public class BlackProggresBar extends ProgressBar {

    private Context context;
    private AttributeSet attrs;
    private int defStyle;

    public BlackProggresBar(Context context) {
        super(context);
        this.context=context;
        init();

    }

    public BlackProggresBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        this.attrs=attrs;
        init();
    }

    public BlackProggresBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        this.attrs=attrs;
        this.defStyle=defStyleAttr;
        init();
    }

    private void init() {
        getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
    }


}
