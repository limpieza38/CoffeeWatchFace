package com.test.moc.coffeecomplication;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;
import android.util.Log;


import com.test.moc.coffeefirebasemessaging.Model.CoffeeDataMessage;
import com.test.moc.coffeefirebasemessaging.firebaseMessagingService.CoffeeFirebaseMessagingService;

import java.util.Locale;

public class CoffeeComplicationProviderService extends ComplicationProviderService {
    private static final String TAG = "CoffeeComplicationProviderService";


    @Override
    public void onComplicationActivated(
            int complicationId, int dataType, ComplicationManager complicationManager) {
        super.onComplicationActivated(complicationId, dataType, complicationManager);
        Log.d(TAG, "onComplicationActivated(): " + complicationId);
    }


    /*
     * Called when the complication needs updated data from your provider. There are four scenarios
     * when this will happen:
     *
     *   1. An active watch face complication is changed to use this provider
     *   2. A complication using this provider becomes active
     *   3. The period of time you specified in the manifest has elapsed (UPDATE_PERIOD_SECONDS)
     *   4. You triggered an update from your own class via the
     *       ProviderUpdateRequester.requestUpdate() method.
     */
    @SuppressLint("Range")
    @Override
    public void onComplicationUpdate(
            int complicationId, int dataType, ComplicationManager complicationManager) {

        Log.d(TAG, "onComplicationUpdate() id: " + complicationId);

        SharedPreferences preferences  = getSharedPreferences(CoffeeMessageBroadcastReceiver.COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY, 0);
        String type = preferences.getString(CoffeeMessageBroadcastReceiver.PREFERENCE_KEY+CoffeeFirebaseMessagingService.TYPE, null);
        String timestamp = preferences.getString(CoffeeMessageBroadcastReceiver.PREFERENCE_KEY+CoffeeFirebaseMessagingService.TIMESTAMP, null);
        String fillLevel = preferences.getString(CoffeeMessageBroadcastReceiver.PREFERENCE_KEY+CoffeeFirebaseMessagingService.FILLLEVEL, null);



        CoffeeDataMessage coffeeDataMessage = new CoffeeDataMessage(type, timestamp, fillLevel);
        String tText = String.format(Locale.getDefault(), "%d!", 0);
        ComplicationData complicationData =
                new ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                        .setShortText(ComplicationText.plainText(tText))
                        .build();

        if(coffeeDataMessage.getType() != CoffeeDataMessage.COFFEE_MESSAGE_TYPE_INVALID) {
            switch (dataType) {
                case ComplicationData.TYPE_SHORT_TEXT:
                    String typeText = String.format(Locale.getDefault(), "%d!", coffeeDataMessage.getType());
                    complicationData =
                            new ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                                    .setShortText(ComplicationText.plainText(typeText))
                                    .build();
                    break;
                default:
                    if (Log.isLoggable(TAG, Log.WARN)) {
                        Log.w(TAG, "Unexpected complication type " + dataType);
                    }
            }
        }

        if (complicationData != null) {
            complicationManager.updateComplicationData(complicationId, complicationData);

        } else {
            // If no data is sent, we still need to inform the ComplicationManager, so the update
            // job can finish and the wake lock isn't held any longer than necessary.
            complicationManager.noUpdateRequired(complicationId);
        }
    }

    /*
     * Called when the complication has been deactivated.
     */
    @Override
    public void onComplicationDeactivated(int complicationId) {
        Log.d(TAG, "onComplicationDeactivated(): " + complicationId);
        // FirebaseMessaging.getInstance().unsubscribeFromTopic("coffee");
    }
}
