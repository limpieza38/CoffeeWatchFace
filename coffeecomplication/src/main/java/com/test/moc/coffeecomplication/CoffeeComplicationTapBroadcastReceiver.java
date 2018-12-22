package com.test.moc.coffeecomplication;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.util.Log;

public class CoffeeComplicationTapBroadcastReceiver extends BroadcastReceiver{
        private static final String TAG = "CoffeeComplicationTapBroadcastReceiver";
    private static final String EXTRA_PROVIDER_COMPONENT =
            "com.example.android.wearable.watchface.provider.action.PROVIDER_COMPONENT";
    private static final String EXTRA_COMPLICATION_ID =
            "com.example.android.wearable.watchface.provider.action.COMPLICATION_ID";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "OnReceive");
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
}
