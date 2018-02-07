package com.clearsoft.welivre.core.utils;

import android.content.Context;

/**
 * Created by on 16.01.17.
 */

public class LanguageUtils {

    public static String getLang(Context context){
        String lang;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            lang = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        }else {
            lang = context.getResources().getConfiguration().locale.getLanguage();
        }
       /* if (lang.equals("ln") || lang.equals("ru") || lang.equals("en")){
            return lang;
        }else {
            lang = "en";
        }*/
        return lang;
    }
}
