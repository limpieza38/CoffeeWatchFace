package com.test.moc.coffeecomplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.test.moc.coffeefirebasemessaging.Model.CoffeeDataMessage;
import com.test.moc.coffeefirebasemessaging.firebaseMessagingService.CoffeeFirebaseMessagingService;

public class CoffeeComplicationBroadcastReceiver extends BroadcastReceiver {

    private CoffeeDataMessage coffeeDataMessage;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String type = bundle.getString(CoffeeFirebaseMessagingService.TYPE);
            String timestamp = bundle.getString(CoffeeFirebaseMessagingService.TIMESTAMP);
            String fillLevel = bundle.getString(CoffeeFirebaseMessagingService.FILLLEVEL);
            CoffeeDataMessage coffeeDataMessage = new CoffeeDataMessage(type, timestamp, fillLevel);
        }
    }
}
