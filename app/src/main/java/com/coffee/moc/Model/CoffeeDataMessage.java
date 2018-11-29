package com.coffee.moc.Model;

import android.util.Log;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Map;

public class CoffeeDataMessage {
    private static final String TAG = "CoffeeDataMessage";
    private String type;
    private int fillLevel;
    private Date date;

    public String getType() {
        return type;
    }

    public int getFillLevel() {
        return fillLevel;
    }

    public Date getDate() {
        return date;
    }

    public CoffeeDataMessage(String iType, String iTimestemp, String iFillLevel) {
        if (iType != null) {
            type = iType;
            if (iTimestemp != null) {
                // TODO Use Timestamp
               /* TimeZone tz = TimeZone.getTimeZone("UTC");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
                format.setTimeZone(tz);
                try {
                    date = format.parse(remoteMessage.get("timestamp"));
                } catch (ParseException e) {
                    date = new Date();
                    Log.w(TAG, "No valid Timestamp");
                } */
                date = new Date();
            } else {
                date = new Date();
                Log.e(TAG, "No Timestamp");
            }
            if (type.equals("fill_level") && iFillLevel != null) {
                    fillLevel = Integer.parseInt(iFillLevel);
            }
        } else {
            throw new InvalidParameterException("No message type");
        }
    }

}
