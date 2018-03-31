package com.example.james.wodrecordapp;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.widget.TextView;

import com.example.james.wodrecordapp.ui.main.ui.MainActivity;

/**
 * Created by jimmy on 28/10/2017.
 */

@RunWith(AndroidJUnit4.class)
public class RecordWodScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> recordWODActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    public static BoundedMatcher<View, TextView> matchesPattern(final String pattern, final String view) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            protected boolean matchesSafely(TextView editText) {
                final Pattern sPattern = Pattern.compile(pattern);

                return sPattern.matcher(editText.getText().toString()).matches();
            }

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("Looked for " + pattern + " in " + view + " view");
            }
        };
    }

    @Test
    public void recordWodActivity_AddWod() throws Exception {
        // Declare the views needed
        ViewInteraction sublimePicker = onView(withId(R.id.sublime_picker));
        ViewInteraction okButton = onView(withId(R.id.buttonPositive));
        ViewInteraction txt_wod_date = onView(withId(R.id.txt_wod_date));


        // Check dateTime picker displays initially
        sublimePicker.check(matches(isDisplayed()));

        // Check Ok button is present
        okButton.check(matches(isDisplayed()));

        // Press the Ok Button
        okButton.perform(click());

        // DateTime Picker should disappear
        sublimePicker.check(doesNotExist());

        // Check a date has been entered
        txt_wod_date.check(matches(matchesPattern("[0-9]{4}-[0-9]{2}-[0-9]{2}", "txt_wod_date")));

        // Declare the views needed
        ViewInteraction txt_weight = onView(withId(R.id.txt_weight));
        ViewInteraction txt_wod_details = onView(withId(R.id.txt_wod_details));
        ViewInteraction txt_wod_time = onView(withId(R.id.txt_wod_time));
        ViewInteraction btn_add_wod = onView(withId(R.id.btn_add_wod));

        // Add additional information
        txt_weight.perform(typeText("30"), closeSoftKeyboard());
        txt_wod_details.perform(typeText("Murph"), closeSoftKeyboard());
        txt_wod_time.perform(typeText("20:13"), closeSoftKeyboard());

        btn_add_wod.perform(click());
    }
}
