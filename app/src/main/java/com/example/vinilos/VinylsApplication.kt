package com.example.vinilos

import android.app.Application
import com.example.vinilos.data.container.AppContainer
import com.example.vinilos.data.container.DefaultAppContainer

class VinylsApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}