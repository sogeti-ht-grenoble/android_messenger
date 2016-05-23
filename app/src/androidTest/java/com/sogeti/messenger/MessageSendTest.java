package com.sogeti.messenger;

import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MessageSendTest
        extends ActivityInstrumentationTestCase2<HomeActivity>{

    private HomeActivity mActivity;
    private String mTextToSend;
    private String mTextSent;


    public MessageSendTest() {
        super(HomeActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
        mTextToSend = "Bonjour";
        mTextSent = "";
    }

    @Test
    public void sendMessage() throws InterruptedException {

        mActivity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent intent) {
                // Assert
                assertThat(intent.hasExtra(MessageSender.BUNDLE_MESSAGE_SENDER),
                        equalTo(true));
                mTextSent = intent.getStringExtra(MessageSender.BUNDLE_MESSAGE_CONTENT);
            }
        }, new IntentFilter(MessageSender.ACTION_MESSAGE));

        onView(withId(R.id.home_input))
                .perform(typeText(mTextToSend), closeSoftKeyboard());
        onView(withId(R.id.home_button))
                .perform(click());
        Thread.sleep(1000);
        assertThat(mTextSent, equalTo(mTextToSend));
    }
}

