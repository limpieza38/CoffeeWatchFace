package com.coffee.moc.firebaseMessagingService;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class CoffeeFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {

        Log.d(TAG, "Refreshed token: " + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "firebase From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
       if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "firebase Message data payload: " + remoteMessage.getData());

        /*    if ( true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            } */

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "firebase Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
