package com.example.taskmaster;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public ActivityScenarioRule<TaskDetailPage> activityRules =
            new ActivityScenarioRule<>(TaskDetailPage.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withId(R.id.button5)).perform(click());
    }

    @Test
    public void textView(){
        onView(withId(R.id.textView2)).check(matches(ViewMatchers.withText("Welcome baraa")));
    }
    @Test
    public void isDsiplay(){
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
    }
    @Test
    public void backToMain(){
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
    }

    @Test
    public void testSettingBut(){
        String username="baraa";
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText(username));
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("Welcome "+username)));

    }
    @Test
    public void addTaskAndShowItInRecyclerView(){
        String task="baraa";
        String desc="test";
        String state="test";
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editText)).perform(typeText(task),closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText(desc),closeSoftKeyboard());
        onView(withId(R.id.stateinput)).perform(typeText(state),closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.datarcyclerview)).check(matches(isDisplayed()));

    }
    @Test
    public void ShowDetails(){
        onView(ViewMatchers.withId(R.id.datarcyclerview)).check(matches(isDisplayed()));
        onView(withId(R.id.datarcyclerview)).perform(actionOnItemAtPosition(0,click()));
        String task="baraa";
        String desc="test";
        String state="test";
        onView(withId(R.id.textView9)).check(matches(withText(task)));
        onView(withId(R.id.textView5)).check(matches(withText(desc)));
        onView(withId(R.id.textView7)).check(matches(withText(state)));

    }
    @Test
    public void changeUserName(){
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText("Baraa"),closeSoftKeyboard());
        onView(withId(R.id.button4)).perform(click());
    }



}