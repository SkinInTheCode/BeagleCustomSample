package com.example.beaglecustomsamplesandroid

import android.app.Application
import com.example.serverdriven.ServerDrivenInitializer

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ServerDrivenInitializer.initialize(this)
    }
}