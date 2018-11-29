package com.coffee.moc.coffeewatchface;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class CoffeeWatchFaceApp extends Application {

    private static final String TAG = "CoffeeWatchFaceApp";
    @Override
    public void onCreate() {
        super.onCreate();
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
}
