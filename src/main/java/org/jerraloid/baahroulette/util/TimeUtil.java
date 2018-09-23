package org.jerraloid.baahroulette.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final int WEEK_MILLIS = 7 * DAY_MILLIS;
    //private static final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    /**
     * Converts time in milliseconds to seconds/minutes/hours/days/weeks
     * 
     * @param time time in milliseconds
     * @return seconds/minutes/hours/days/weeks
     */
    public static String getRelativeTime(long time) {
    	//gets the times seconds, minutes, hours, days and weeks
        int seconds = (int) (time / SECOND_MILLIS) % 60 ;
        int minutes = (int) (time / MINUTE_MILLIS) % 60;
        int hours   = (int) (time / HOUR_MILLIS) % 24;
        int days	= (int) (time / DAY_MILLIS) % 7;
        int weeks	= (int) (time / WEEK_MILLIS);
        
        //returns time
        if (time < MINUTE_MILLIS) {
            return seconds +  " seconds";
        } else if (time < HOUR_MILLIS) {
        	return minutes + " minutes and " + seconds + " seconds";
        } else if (time < DAY_MILLIS) {
            return hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
        } else if(time < WEEK_MILLIS) { 
        	return days + " days, " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
        } else {
        	return weeks + " weeks, " + days + " days, " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
        }
    }
    
    /**
     * Converts milliseconds to a timestamp
     * 
     * @param time time in milliseconds
     * @return timestamp
     */
    public static String getTimestampFormat(long time) {
        try {
            return timestamp.format(new Date(time));
        } catch (Exception ex) {
            return "cant figure out (" + time + ")";
        }
    }
}
