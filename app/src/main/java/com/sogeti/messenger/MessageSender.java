package com.sogeti.messenger;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;

/**
 * Created by emsari on 23/05/2016.
 */
public class MessageSender {

    public static final String ACTION_MESSAGE = "com.sogeti.messenger.ACTION_MESSAGE";
    public static final String BUNDLE_MESSAGE_SENDER = "com.sogeti.messenger.BUNDLE_MESSAGE_SENDER";
    public static final String BUNDLE_MESSAGE_CONTENT = "com.sogeti.messenger.BUNDLE_MESSAGE_CONTENT";

    private final String message;
    private final Context context;

    public MessageSender(Context context, String message) {
        this.message = message;
        this.context = context;
    }

    public void send() {
        Intent sendIntent = new Intent(ACTION_MESSAGE);
        sendIntent.putExtra(BUNDLE_MESSAGE_CONTENT, message);
        sendIntent.putExtra(BUNDLE_MESSAGE_SENDER, getDeviceId());
        context.sendBroadcast(sendIntent);
    }

    private String getDeviceId() {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }
}
