package com.clearsoft.welivre.core.utils;

import android.content.Context;

import com.clearsoft.welivre.R;


/**
 * Created by mmhryshchuk on 03.08.16.
 */
public final class ConverterUtils {

    public static String secToStringTime(Context context, long sec){
        int hours = (int) sec / 3600;
        int reminder = (int) sec - hours * 3600;
        int minutes = reminder / 60;
        if (hours == 0){
            return String.format("%d %s", minutes, context.getString(R.string.min));
        }
        return String.format("%d %s%d %s", hours, context.getString(R.string.hour), minutes, context.getString(R.string.min));
    }

    //// TODO: 10.10.16 Change to get strings from string.xml
    public static String byteToMb(Context context, long bytes,boolean si){
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " б";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "кМГT" : "кМГT").charAt(exp-1) + (si ? "" : "");
        return String.format("%.1f %sб", bytes / Math.pow(unit, exp), pre);
    }

    public static String getPercentage(long full, long part){
        if (part >= full) return "100%";

        long percentage = (int)(part * 100d / full);
        return percentage + "%";
    }
}
