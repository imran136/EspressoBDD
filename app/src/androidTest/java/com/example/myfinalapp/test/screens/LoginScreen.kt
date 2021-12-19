package com.example.myfinalapp.test.screens

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.myfinalapp.MainActivity
import com.example.myfinalapp.R

class LoginScreen {


    fun launchLoginScreen(testRule: ActivityTestRule<MainActivity>) {
        testRule.launchActivity(null)
    }

    fun selectEmailField() {
        onView(withId(R.id.user_name)).perform(click())
    }

    fun selectPasswordField() {
        onView(withId(R.id.password)).perform(click())
    }

    fun enterEmail(email: String) {
        onView(withId(R.id.user_name)).perform(typeText(email))
    }

    fun enterPassword(password: String) {
        onView(withId(R.id.password)).perform(typeText(password))
    }

    fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
        Thread.sleep(100)
    }

    fun clickSignInButton() {
        onView(withId(R.id.button_submit)).perform(click())
    }

    fun isSuccessfulLogin() {
        onView(withId(R.id.successful_login_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.successful_login_text_view)).check(matches(withText(R.string.successful_login)))
    }

}