package com.example.myfinalapp.test.steps


import androidx.test.rule.ActivityTestRule
import com.example.myfinalapp.MainActivity
import com.example.myfinalapp.test.screens.LoginScreen
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When


class LoginActivitySteps {

    private val loginScreen = LoginScreen()

    private val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        //not needed now, but you may needed to setup mock responses before your screen starts
    }

    @After
    fun tearDown() {
        activityRule.finishActivity()
        //ActivityFinisher.finishOpenActivities() // Required for test scenarios with multiple activities or scenarios with more cases
    }

    @Given("^I start the application$")
    fun i_start_app() {
        loginScreen.launchLoginScreen(activityRule)
    }

    @When("^I click email field$")
    fun i_click_email_field() {
        loginScreen.selectEmailField()
    }

    @And("^I close the keyboard$")
    fun i_close_the_keyboard() {
        loginScreen.closeKeyboard()
    }

    @And("^I enter valid email (\\S+)$")
    fun i_enter_valid_email(email: String) {
        loginScreen.enterEmail(email)
    }

    @And("^I click password field$")
    fun i_click_password_field() {
        loginScreen.selectPasswordField()
    }

    @And("^I enter valid password (\\S+)$")
    fun i_enter_valid_password(password: String) {
        loginScreen.enterPassword(password)
    }

    @And("^I click sign in button$")
    fun i_click_sign_in_button() {
        loginScreen.clickSignInButton()
    }

    @Then("^I expect to see successful login message$")
    fun i_expect_to_see_successful_login_message() {
        loginScreen.isSuccessfulLogin()
    }

}
