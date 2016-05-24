package com.sogeti.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

/**
 * Created by emsari on 24/05/2016.
 */
public class MessageReceiver extends BroadcastReceiver {

    private final MessageDisplayer displayer;

    public MessageReceiver(@NonNull MessageDisplayer displayer) {
        this.displayer = displayer;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MessagingConstants.ACTION_MESSAGE.equals(intent.getAction())) {
            String sender = intent.getStringExtra(MessagingConstants.BUNDLE_MESSAGE_SENDER);
            String content = intent.getStringExtra(MessagingConstants.BUNDLE_MESSAGE_CONTENT);
            displayer.displayMessage(sender, content);
        }
    }

    public IntentFilter getIntentFilter() {
        return new IntentFilter(MessagingConstants.ACTION_MESSAGE);
    }
}
