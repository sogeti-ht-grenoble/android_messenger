package com.sogeti.messenger;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;

/**
 * Created by emsari on 23/05/2016.
 */
public class MessageSender {

    private final String message;
    private final Context context;

    public MessageSender(Context context, String message) {
        this.message = message;
        this.context = context;
    }

    public void send() {
        Intent sendIntent = new Intent(MessagingConstants.ACTION_MESSAGE);
        sendIntent.putExtra(MessagingConstants.BUNDLE_MESSAGE_CONTENT, message);
        sendIntent.putExtra(MessagingConstants.BUNDLE_MESSAGE_SENDER, getDeviceId());
        context.sendBroadcast(sendIntent);
    }

    private String getDeviceId() {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }
}
