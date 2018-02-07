package com.clearsoft.welivre.core.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created 26.01.2016.
 */
public abstract class BasePreference {

    protected abstract Context getContext();

    protected void save(String key, String value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.edit().putString(key, value).commit();
    }

    protected void save(String key, long value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.edit().putLong(key, value).commit();
    }

    protected void save(String key, int value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.edit().putInt(key, value).commit();
    }

    protected void remove(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.edit()
                .remove(key)
                .commit();
    }

    protected String getStr(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getString(key, "");
    }

    protected long getLong(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getLong(key, 0);
    }

    protected long getLongPage(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getLong(key, -1);
    }
    protected int getInt(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getInt(key, 0);
    }

    protected boolean getBoolean(String key, boolean def){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getBoolean(key, def);
    }
    protected void save(String key, boolean value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.edit().putBoolean(key, value).commit();
    }

    protected SharedPreferences getPreference(){
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }
}
