package com.example.myfinalapp.test

import android.os.Bundle
import androidx.test.runner.MonitoringInstrumentation
import com.example.myfinalapp.BuildConfig
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore


@CucumberOptions(features = ["features"], glue = ["com.example.myfinalapp.test"])
class Instrumentation : MonitoringInstrumentation() {
    private val instrumentationCore = CucumberInstrumentationCore(this)
    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)
        val tags: String = BuildConfig.TEST_TAGS
        if (tags.isNotEmpty()) {
            arguments.putString(
                "tags",
                tags.replace(",".toRegex(), "--").replace("\\s".toRegex(), "")
            )
        }
        instrumentationCore.create(arguments)
        start()
    }

    override fun onStart() {
        super.onStart()
        waitForIdleSync()
        instrumentationCore.start()
    }
}
