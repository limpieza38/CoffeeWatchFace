package com.cf.moc.coffeefirebasemessaging.Model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class CoffeeDataMessage {
    private static final String TAG = "CoffeeDataMessage";
    private final static SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final int COFFEE_MESSAGE_TYPE_BREWING = 1;
    public static final int COFFEE_MESSAGE_TYPE_READY = 2;
    public static final int COFFEE_MESSAGE_TYPE_FILL_LEVEL = 3;
    public static final int COFFEE_MESSAGE_TYPE_INVALID = 0;

    private int type = COFFEE_MESSAGE_TYPE_INVALID;
    private int fillLevel;
    private Calendar calendar = null;


    private void setType(String iType) {
        if (iType != null) {
            switch (iType) {
                case "coffee_brewing":
                    type = COFFEE_MESSAGE_TYPE_BREWING;
                    break;
                case "coffee_ready":
                    type = COFFEE_MESSAGE_TYPE_READY;
                    break;
                case "coffee_fill_level":
                    type = COFFEE_MESSAGE_TYPE_FILL_LEVEL;
                    break;
                default:
                    type = COFFEE_MESSAGE_TYPE_INVALID;
            }
        }
    }

    public int getType() {
        return type;
    }

    public int getFillLevel() {
        return fillLevel;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public CoffeeDataMessage(String iType, String iTimestamp, String iFillLevel) {
        ISO8601DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        setType(iType);
        if (type != COFFEE_MESSAGE_TYPE_INVALID){
            if (iTimestamp != null) {
                calendar = Calendar.getInstance(TimeZone.getDefault());
                try {
                    calendar.setTime(ISO8601DATEFORMAT.parse(iTimestamp));
                } catch (ParseException e) {
                    Log.e(TAG, "Error Parsing Timestamp "+iTimestamp);
                    calendar = null;
                }

            } else {
                Log.e(TAG, "No Timestamp");
            }
            if (type == COFFEE_MESSAGE_TYPE_FILL_LEVEL && iFillLevel != null) {
                fillLevel = Integer.parseInt(iFillLevel);
            }
        } else {
            // throw new InvalidParameterException("No message type");
        }
    }

}
