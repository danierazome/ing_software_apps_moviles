package com.example.vinilos

import android.app.Application
import com.example.vinilos.data.AppContainer
import com.example.vinilos.data.DefaultAppContainer

class VinylsApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}