package com.test.moc.coffeecomplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.test.moc.coffeefirebasemessaging.firebaseMessagingService.CoffeeFirebaseMessagingService;

public class CoffeeMessageBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "CoffeeMessageBroadcastReceiver";
    public static final String PREFERENCE_KEY = "com.test.moc.coffeecomplication.CoffeeMessageBroadcastReceiver";

    static final String COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY =
            "com.example.android.wearable.watchface.COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY";
    static final String UPDATE_ACTION =
            "com.test.moc.coffeecomplication.UPDATE_COMPLICATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        Log.d(TAG, intent.getAction());
        Bundle bundle = intent.getExtras();

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY, 0);
        String type = bundle.getString(CoffeeFirebaseMessagingService.TYPE);
        String timestamp = bundle.getString(CoffeeFirebaseMessagingService.TIMESTAMP);
        String fillLevel = bundle.getString(CoffeeFirebaseMessagingService.FILLLEVEL);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_KEY + CoffeeFirebaseMessagingService.TYPE, type);
        editor.putString(PREFERENCE_KEY + CoffeeFirebaseMessagingService.TIMESTAMP, timestamp);
        editor.putString(PREFERENCE_KEY + CoffeeFirebaseMessagingService.FILLLEVEL, fillLevel);
        editor.apply();
    }
}

