package com.example.myfinalapp.test


import android.app.Activity
import android.content.Intent
import android.service.autofill.Validators.not
import androidx.test.rule.ActivityTestRule
import com.example.myfinalapp.MainActivity
import com.example.myfinalapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import android.widget.EditText
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.Assert
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.internal.matchers.TypeSafeMatcher


class LoginActivitySteps {
    @Rule
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)
    private var activity: Activity? = null
    @Before("@login-feature")
    fun setup() {
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After("@login-feature")
    fun tearDown() {
        activityTestRule.finishActivity()
    }

    @Given("^I am on login screen")
    fun i_am_on_login_screen() {
        Assert.assertNotNull(activity)
    }

    @When("^I input email (\\S+)$")
    fun i_input_email(email: String?) {
        onView(withId(R.id.user_name)).perform(typeText(email))
    }

    @When("^I input password \"(.*?)\"$")
    fun i_input_password(password: String?) {
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard())
    }

    @When("^I press submit button$")
    fun i_press_submit_button() {
        onView(withId(R.id.button_submit)).perform(click())
    }

    @Then("^I should see error on the (\\S+)$")
    fun i_should_see_error_on_the_editTextView(viewName: String) {
        val viewId: Int = if (viewName == "email") R.id.user_name else R.id.password
        val messageId: Int =
            if (viewName == "email") R.string.error_invalid_email else R.string.error_invalid_password
        onView(withId(viewId)).check(matches(hasErrorText(activity!!.getString(messageId))))
    }

    @Then("^I should (true|false) auth error$")
    fun i_should_see_auth_error(shouldSeeError: Boolean) {
        if (shouldSeeError) {
            onView(withId(R.id.successful_login_text_view)).check(matches(isDisplayed()))
        } else {
            onView(withId(R.id.successful_login_text_view)).check(matches(isDisplayed()))
        }
    }


    /**
     * Custom matcher to assert equal EditText.setError();
     */
    private class ErrorTextMatcher(private val mExpectedError: String) :
        TypeSafeMatcher<View?>() {
        override fun matchesSafely(view: View?): Boolean {
            if (view !is EditText) {
                return false
            }
            return mExpectedError == view.error.toString()
        }

        override fun describeTo(description: Description) {
            description.appendText("with error: $mExpectedError")
        }
    }

    companion object {
        private fun hasErrorText(expectedError: String): Matcher<in View> {
            return ErrorTextMatcher(expectedError)
        }
    }
}
