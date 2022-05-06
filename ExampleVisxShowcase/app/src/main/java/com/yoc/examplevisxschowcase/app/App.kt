package com.yoc.examplevisxschowcase.app

import android.app.Application
import com.yoc.visx.sdk.VisxSDK

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        VisxSDK.initialize(applicationContext, "90017")
    }
}