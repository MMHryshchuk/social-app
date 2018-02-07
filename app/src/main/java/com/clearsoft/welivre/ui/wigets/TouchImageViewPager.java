package com.clearsoft.welivre.ui.wigets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by on 28.10.16.
 */

public class TouchImageViewPager extends ViewPager{


    public TouchImageViewPager(Context context) {
        super(context);
    }

    public TouchImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof NoDieImageView) {
            //
            // canScrollHorizontally is not supported for Api < 14. To get around this issue,
            // ViewPager is extended and canScrollHorizontallyFroyo, a wrapper around
            // canScrollHorizontally supporting Api >= 8, is called.
            //
            return ((NoDieImageView) v).canScrollHorizontallyFroyo(-dx);

        } else {
            return super.canScroll(v, checkV, dx, x, y);
        }
    }

}
