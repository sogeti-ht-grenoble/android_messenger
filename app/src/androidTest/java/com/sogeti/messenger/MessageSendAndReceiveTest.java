package com.sogeti.messenger;

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
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MessageSendAndReceiveTest
        extends ActivityInstrumentationTestCase2<HomeActivity>{

    private HomeActivity mActivity;
    private String mTextToSend;


    public MessageSendAndReceiveTest() {
        super(HomeActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
        mTextToSend = "Bonjour";
    }

    @Test
    public void sendAndReceiveMessage() throws InterruptedException {

        onView(withId(R.id.home_input))
                .perform(typeText(mTextToSend), closeSoftKeyboard());
        onView(withId(R.id.home_button))
                .perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.home_output))
                .check(matches(withText(containsString(mTextToSend))));
    }
}

