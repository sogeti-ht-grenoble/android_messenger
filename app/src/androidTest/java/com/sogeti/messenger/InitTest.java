package com.sogeti.messenger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InitTest
        extends ActivityInstrumentationTestCase2<HomeActivity>{

    private HomeActivity mActivity;
    private String mInitialText = "Hello World!";

    public InitTest() {
        super(HomeActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void checkInitialTextIsHelloWorld() {
        onView(withId(R.id.home_output))
                .check(matches(withText(mInitialText)));
    }
}

