package com.example.myapplication

import android.app.Application
import com.example.myapplication.dagger_bicycle.DaggerBicycleComponent
import com.example.myapplication.dagger_bicycle.DaggerDaggerBicycleComponent
import com.example.myapplication.dagger_bicycle.FrameFactory
import com.example.myapplication.koin_bicycle.bicycleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    lateinit var dagger : DaggerBicycleComponent
    val frameFactory = FrameFactory()
    override fun onCreate() {
        super.onCreate()
        dagger = DaggerDaggerBicycleComponent.builder().build()
        startKoin{
            androidContext(applicationContext)
            modules(bicycleModule)
        }
    }
}