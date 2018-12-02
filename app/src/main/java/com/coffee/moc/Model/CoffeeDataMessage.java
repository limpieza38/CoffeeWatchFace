package com.coffee.moc.Model;
import android.util.Log;
import java.security.InvalidParameterException;
import java.util.Date;


public class CoffeeDataMessage {
    private static final String TAG = "CoffeeDataMessage";
    public static final int COFFEE_MESSAGE_TYPE_BREWING = 1;
    public static final int COFFEE_MESSAGE_TYPE_READY = 2;
    public static final int COFFEE_MESSAGE_TYPE_FILL_LEVEL = 3;
    public static final int COFFEE_MESSAGE_TYPE_INVALID = 0;

    private int type = COFFEE_MESSAGE_TYPE_INVALID;
    private int fillLevel;
    private Date date;


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

    public Date getDate() {
        return date;
    }

    public CoffeeDataMessage(String iType, String iTimestamp, String iFillLevel) {
        setType(iType);
        if (type != COFFEE_MESSAGE_TYPE_INVALID){
            if (iTimestamp != null) {
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
            if (type == COFFEE_MESSAGE_TYPE_FILL_LEVEL && iFillLevel != null) {
                fillLevel = Integer.parseInt(iFillLevel);
            }
        } else {
            throw new InvalidParameterException("No message type");
        }
    }

}
