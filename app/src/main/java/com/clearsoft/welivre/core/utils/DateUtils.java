package com.clearsoft.welivre.core.utils;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Date utils
 */
public final class DateUtils {
	/**
	 * Defines RFC 822 date-time format.
	 */
	private static final String RFC_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'";
	/**
	 * Parse date object to ISO 8601 string.
	 *
	 * @param milliseconds date object.
	 *
	 * @return formatted string.
	 */
	public static String getISO8601FromMilliseconds(Context context, long milliseconds) {
		DateTime dt = new DateTime(milliseconds)
				.withZoneRetainFields(DateTimeZone.UTC)
				.withZone(DateTimeZone.getDefault());
//		DateTime dt = new LocalDateTime(milliseconds).toDateTime(DateTimeZone.UTC);
		return DateTimeFormat.forPattern(RFC_DATE_TIME_PATTERN)
				.print(dt);
	}

	/**
	 * Get timestamp from string date WITH TIMEZONE PARSED AS OFFSET
	 *
	 * @param date string containing date
	 *
	 * @return timestamp
	 */
	public static Long parseISO8601ToMilliseconds(String date) {
		return DateTimeFormat.forPattern(RFC_DATE_TIME_PATTERN)
				.withOffsetParsed()
				.parseDateTime(date)
				.toLocalDateTime().toDate().getTime();
	}

	public static Long parseDateToMilliseconds(String date) {
		return DateTimeFormat.forPattern(DATE_TIME_PATTERN)
				.withOffsetParsed()
				.parseDateTime(date)
				.toLocalDateTime().toDate().getTime();
	}


	public static String getRegionalDate(Context context, Date date, String skeleton, String simpleDatePattern){
		final Locale locale = context.getResources().getConfiguration().locale;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2)
			simpleDatePattern = android.text.format.DateFormat.getBestDateTimePattern(locale, skeleton);
		return new SimpleDateFormat(simpleDatePattern, locale).format(date);
	}

	public static String getRegionalTime(Context context, Date date, String skeleton, String simpleDatePattern){
		final Locale locale = context.getResources().getConfiguration().locale;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2)
			simpleDatePattern = android.text.format.DateFormat.getTimeFormat(context).format(date);
		else {
			simpleDatePattern = new SimpleDateFormat(simpleDatePattern, locale).format(date);
		}
		return simpleDatePattern;
	}

	public static String getPrettyTimeFormat(String historyLastListened,Context context){
		long time = parseISO8601ToMilliseconds(historyLastListened);
		return getPrettyTimeFormat(time, context);
	}

	public static String getPrettyTimeFormat(long time,Context context){
		Calendar calendarNow = Calendar.getInstance(TimeZone.getDefault());
		PrettyTime prettyTime = new PrettyTime(context.getResources().getConfiguration().locale);
		Calendar calendarHistory = Calendar.getInstance(context.getResources().getConfiguration().locale);
		calendarHistory.setTime(new Date(time * 1000));

		if (calendarHistory.get(Calendar.DATE) == calendarNow.get(Calendar.DATE)){
			calendarHistory.setTime(new Date(calendarHistory.getTimeInMillis() + calendarHistory.getTimeZone().getRawOffset()));
			return prettyTime.format(calendarHistory);
		}else {
			calendarNow.add(Calendar.DATE,-1);
			if (calendarHistory.get(Calendar.DATE) == calendarNow.get(Calendar.DATE)){
				calendarHistory.setTime(new Date(calendarHistory.getTimeInMillis() + TimeZone.getDefault().getRawOffset()));
				return prettyTime.format(calendarHistory)  + " " +getRegionalTime(context,calendarHistory.getTime(),"HH:mm","HH:mm");
			} else if (calendarHistory.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)){
				calendarHistory.setTime(new Date(calendarHistory.getTimeInMillis() + TimeZone.getDefault().getRawOffset()));
				return getRegionalDate(context,calendarHistory.getTime(),"d MMMM","d MMMM") + " " + getRegionalTime(context,calendarHistory.getTime(),"HH:mm","HH:mm");
			}else {
				calendarHistory.setTime(new Date(calendarHistory.getTimeInMillis() + TimeZone.getDefault().getRawOffset()));
				return getRegionalDate(context,calendarHistory.getTime(),"d MMMM yyyy","d MMMM yyyy");
			}
		}
	}
}
