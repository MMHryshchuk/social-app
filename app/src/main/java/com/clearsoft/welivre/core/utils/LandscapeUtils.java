package com.clearsoft.welivre.core.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.clearsoft.welivre.R;


/**
 * Created by on 28.10.16.
 */

public final class LandscapeUtils  {

    public static boolean isTablet(Context context){
        if( context.getResources().getBoolean(R.bool.isTablet)){
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }


    public static boolean isTablet2(Context context){
        if( context.getResources().getBoolean(R.bool.isTablet)){
            return true;
        }
        return false;
    }

}
