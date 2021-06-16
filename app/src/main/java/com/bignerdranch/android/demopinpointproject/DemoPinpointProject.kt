package com.bignerdranch.android.demopinpointproject

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.analytics.AnalyticsEvent
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify

class DemoPinpointProject : Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSPinpointAnalyticsPlugin(this))
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }

        val event1 = AnalyticsEvent.builder()
            .name("Test Event 1")
            .addProperty("Channel", "SMS")
            .addProperty("Successful", true)
            .addProperty("ProcessDuration", 792)
            .addProperty("UserAge", 120.3)
            .build()

        Log.v("Test Event 1", "value" + event1)
        Amplify.Analytics.recordEvent(event1)

        val event2 = AnalyticsEvent.builder()
            .name("Test Event 2")
            .addProperty("Channel", "Text")
            .addProperty("Successful", false)
            .addProperty("ProcessDuration", 452)
            .addProperty("UserAge", 150.0)
            .build()

        Log.v("Test Event 2", "value" + event2)
        Amplify.Analytics.recordEvent(event2)

        val event3 = AnalyticsEvent.builder()
            .name("Test Event 3")
            .addProperty("Channel", "Test")
            .addProperty("Successful", true)
            .addProperty("ProcessDuration", 342)
            .addProperty("UserAge", 89.2)
            .build()

        Log.v("Test Event 3", "value" + event3)
        Amplify.Analytics.recordEvent(event3)

    }
}