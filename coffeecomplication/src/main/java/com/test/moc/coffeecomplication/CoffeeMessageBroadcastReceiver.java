package com.test.moc.coffeecomplication;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.util.Log;


import com.test.moc.coffeefirebasemessaging.firebaseMessagingService.CoffeeFirebaseMessagingService;

public class CoffeeMessageBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "CoffeeMessageBroadcastReceiver";
    public static final String PREFERENCE_KEY = "com.test.moc.coffeecomplication.CoffeeMessageBroadcastReceiver";
    private static final String EXTRA_PROVIDER_COMPONENT =
            "com.example.android.wearable.watchface.provider.action.PROVIDER_COMPONENT";
    private static final String EXTRA_COMPLICATION_ID =
            "com.example.android.wearable.watchface.provider.action.COMPLICATION_ID";

    static final String COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY =
            "com.example.android.wearable.watchface.COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Coffee test test 123");
        Bundle bundle = intent.getExtras();

        String type = bundle.getString(CoffeeFirebaseMessagingService.TYPE);
        String timestamp = bundle.getString(CoffeeFirebaseMessagingService.TIMESTAMP);
        String fillLevel = bundle.getString(CoffeeFirebaseMessagingService.FILLLEVEL);

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY, 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_KEY+CoffeeFirebaseMessagingService.TYPE, type);
        editor.putString(PREFERENCE_KEY+CoffeeFirebaseMessagingService.TIMESTAMP, timestamp);
        editor.putString(PREFERENCE_KEY+CoffeeFirebaseMessagingService.FILLLEVEL, fillLevel);
        editor.apply();
    }


    /**
     * Returns a pending intent, suitable for use as a tap intent, that causes a complication to be
     * toggled and updated.
     */

}
