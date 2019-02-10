package com.cf.moc.coffeecomplication;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.util.Log;
import android.widget.Toast;

import com.cf.moc.coffeefirebasemessaging.Model.CoffeeDataMessage;
import com.cf.moc.coffeefirebasemessaging.firebaseMessagingService.CoffeeFirebaseMessagingService;

import java.util.Calendar;

import static com.cf.moc.coffeecomplication.CoffeeMessageBroadcastReceiver.COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY;

public class CoffeeComplicationTapBroadcastReceiver extends BroadcastReceiver{
        private static final String TAG = "CoffeeComplicationTapBroadcastReceiver";
    private static final String EXTRA_PROVIDER_COMPONENT =
            "com.example.android.wearable.watchface.provider.action.PROVIDER_COMPONENT";
    private static final String EXTRA_COMPLICATION_ID =
            "com.example.android.wearable.watchface.provider.action.COMPLICATION_ID";
    public static final String UPDATE_BY_TAP = "UPDATE_BY_TAP";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "OnReceive");
        makeToastWithDetails(context);

        Bundle bundle = intent.getExtras();
        ComponentName provider = bundle.getParcelable(EXTRA_PROVIDER_COMPONENT);
        ProviderUpdateRequester updateRequester = new ProviderUpdateRequester(context, provider);
        updateRequester.requestUpdateAll();
    }

    /**
     * Returns a pending intent, suitable for use as a tap intent, that causes a complication to be
     * toggled and updated.
     */
    static PendingIntent getToggleIntent(
            Context context, ComponentName provider, int complicationId) {
        Intent intent = new Intent(context, CoffeeComplicationTapBroadcastReceiver.class);
        intent.putExtra(EXTRA_PROVIDER_COMPONENT, provider);
        intent.putExtra(EXTRA_COMPLICATION_ID, complicationId);

        // Pass complicationId as the requestCode to ensure that different complications get
        // different intents.
        return PendingIntent.getBroadcast(
                context, complicationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void makeToastWithDetails(Context context){
        SharedPreferences preferences =
                context.getSharedPreferences(COMPLICATION_PROVIDER_PREFERENCES_FILE_KEY, 0);
        String type = preferences.getString(CoffeeMessageBroadcastReceiver.PREFERENCE_KEY + CoffeeFirebaseMessagingService.TYPE, null);
        String timestamp = preferences.getString(CoffeeMessageBroadcastReceiver.PREFERENCE_KEY + CoffeeFirebaseMessagingService.TIMESTAMP, null);
        String fillLevel = preferences.getString(CoffeeMessageBroadcastReceiver.PREFERENCE_KEY + CoffeeFirebaseMessagingService.FILLLEVEL, null);
        CoffeeDataMessage coffeeDataMessage = new CoffeeDataMessage(type, timestamp, fillLevel);
        String message = selectCoffeeToastMessage(context,coffeeDataMessage);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    private String selectCoffeeToastMessage(Context context, CoffeeDataMessage coffeeDataMessage) {
        Resources res = context.getResources();
        String message =res.getString(R.string.tapMessageEmpty);

        if (coffeeDataMessage != null) {
            Calendar today = Calendar.getInstance();
            long seconds = (today.getTimeInMillis() - coffeeDataMessage.getCalendar().getTimeInMillis()) / 1000;
            int hours = (int) (seconds / 3600);
            // int minutes = (int) (seconds- hours * 3600) /60;
            switch (coffeeDataMessage.getType()) {
                case CoffeeDataMessage.COFFEE_MESSAGE_TYPE_FILL_LEVEL:
                    message = String.format(res.getString(R.string.tapMessageFillLevel), coffeeDataMessage.getFillLevel(), hours);
                    break;
                case CoffeeDataMessage.COFFEE_MESSAGE_TYPE_READY:
                    message = String.format(res.getString(R.string.tapMessageReady), hours);
                    break;
                case CoffeeDataMessage.COFFEE_MESSAGE_TYPE_BREWING:
                    message = String.format(res.getString(R.string.tapMessageBrewing), hours);
                    break;
            }

        }
        return message;
    }
}
