package com.cf.moc.coffeecomplication;

import android.app.Application;
import android.app.PendingIntent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.cf.moc.coffeefirebasemessaging.firebaseMessagingService.CoffeeFirebaseMessagingService;

public class CoffeeComplicationApp extends Application {
    CoffeeMessageBroadcastReceiver coffeeMessageBroadcastReceiver = new CoffeeMessageBroadcastReceiver();

    private static final String TAG = "CoffeeComplicationApp";

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter coffeeFilter = new IntentFilter(CoffeeFirebaseMessagingService.NOTIFICATION);
        registerReceiver(coffeeMessageBroadcastReceiver, coffeeFilter);

        FirebaseMessaging.getInstance().subscribeToTopic("coffee")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Succesfully joined to topic coffee";
                        if (!task.isSuccessful()) {
                            msg = "Failed joining topic coffee";
                        }
                        Log.d(TAG, msg);
                    }
                });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(coffeeMessageBroadcastReceiver);

        FirebaseMessaging.getInstance().unsubscribeFromTopic("coffee");
    }
}
