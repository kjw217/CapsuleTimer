package com.mobile_term_project.knight.a2018_mobile;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by leejisung on 2018-06-07.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AppInfoTest {

    @Rule
    public ActivityTestRule<AppInfo> activityTestRule = new ActivityTestRule<AppInfo>(AppInfo.class);

    @Test
    public void onCreate() throws Exception {

        onView(withText("버젼 1.0.0.0")).check(matches(isDisplayed()));
        onView(withId(R.id.textView3)).perform(click());


    }

}