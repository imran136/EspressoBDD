package com.example.myfinalapp

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        val userEmail = findViewById<EditText>(R.id.user_name)
        val password = findViewById<EditText>(R.id.password)
        val buttonReset = findViewById<Button>(R.id.button_reset)
        val buttonSubmit = findViewById<Button>(R.id.button_submit)
        val successfulLoginMessage = findViewById<TextView>(R.id.successful_login_text_view)

        // set on-click listener
        buttonReset.setOnClickListener {
            // clearing user_name and password,
            // and making any shown success message invisible on reset button click
            userEmail.setText("")
            password.setText("")
            successfulLoginMessage.visibility = View.INVISIBLE
        }

        // set on-click listener
        buttonSubmit.setOnClickListener {
            // code to validate the user_name and password combination
            // and verify the same
            attemptLogin(userEmail, password, successfulLoginMessage)
        }
    }

    private fun attemptLogin(userEmail: EditText, password: EditText, successfulLoginMessage: TextView) {
        // Reset errors.
        userEmail.error = null
        password.error = null

        val userEmailStr = userEmail.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Checking for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && isPasswordValid(passwordStr)!=true) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(userEmailStr)) {
            userEmail.error = getString(R.string.empty_field_required)
            focusView = userEmail
            cancel = true
        } else if (isEmailValid(userEmailStr)!=true) {
            userEmail.error = getString(R.string.error_invalid_email)
            focusView = userEmail
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            successfulLoginMessage.visibility = View.VISIBLE
        }
    }

    private fun isPasswordValid(passwordStr: String): Any {
        return passwordStr.length > 6
    }


    private fun isEmailValid(emailStr: String): Any {
        return emailStr.contains("@")
    }


}