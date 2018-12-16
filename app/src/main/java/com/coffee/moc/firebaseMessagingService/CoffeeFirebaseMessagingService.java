package com.coffee.moc.firebaseMessagingService;

import android.content.Intent;
import android.util.Log;

import com.coffee.moc.Model.CoffeeDataMessage;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class CoffeeFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "CoffeeFirebaseMessagingService";
    public static final String NOTIFICATION = "com.coffee.moc.firebaseMessagingService";
    public static final String TYPE = "type";
    public static final String TIMESTAMP = "timestamp";
    public static final String FILLLEVEL = "fillLevel";
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "firebase From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "firebase Message data payload: " + remoteMessage.getData());

            Map<String, String> data = remoteMessage.getData();

            // Handle message within 10 seconds
            handleRemoteDataMessage(data);

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null)

        {
            Log.d(TAG, "firebase Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    private void handleRemoteDataMessage(Map<String, String> data) {
        Intent intent = new Intent(NOTIFICATION);
        if(data.containsKey(TYPE)){
            intent.putExtra(TYPE, data.get(TYPE));
        }
        if(data.containsKey(TIMESTAMP)){
            intent.putExtra(TIMESTAMP, data.get(TIMESTAMP));
        }
        if(data.containsKey(FILLLEVEL)){
            intent.putExtra(FILLLEVEL, data.get(FILLLEVEL));
        }
        sendBroadcast(intent);
    }
}
