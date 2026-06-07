package com.project.tickr

import android.app.Application
import com.project.tickr.core.network.ChuckerContext

class TickrApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ChuckerContext.applicationContext = applicationContext
    }
}
