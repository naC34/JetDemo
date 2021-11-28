package com.nac.jetdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}