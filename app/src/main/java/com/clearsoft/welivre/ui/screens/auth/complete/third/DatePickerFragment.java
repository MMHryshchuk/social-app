package com.clearsoft.welivre.ui.screens.auth.complete.third;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.clearsoft.welivre.core.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by on 29.06.17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private DateListener listener;

    public DatePickerFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        if (dayOfMonth < 10) {
            listener.returnDate(year + "-" + month + "-0" + dayOfMonth);
        }else {
            listener.returnDate(year + "-" + month + "-" + dayOfMonth);
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, 0);
        listener.returnDateMilisecond(cal.getTimeInMillis());
    }
    public void setListener(DateListener listener){
        this.listener = listener;
    }

    public interface DateListener {
        void returnDate(String date);
        void returnDateMilisecond(long milisecond);
    }
}
