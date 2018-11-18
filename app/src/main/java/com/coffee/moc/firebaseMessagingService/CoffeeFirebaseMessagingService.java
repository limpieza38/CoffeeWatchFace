package com.coffee.moc.firebaseMessagingService;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import static android.content.ContentValues.TAG;

public class CoffeeFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {

        Log.d(TAG, "Refreshed token: " + token);
    }
}
