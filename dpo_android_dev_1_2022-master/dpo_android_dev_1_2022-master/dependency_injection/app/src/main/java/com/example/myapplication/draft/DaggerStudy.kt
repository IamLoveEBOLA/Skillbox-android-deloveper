package com.example.myapplication.draft

import com.example.myapplication.dagger_bicycle.BicycleModule
import com.example.myapplication.dagger_bicycle.DaggerBicycleComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class Info (val text : String)


@Module
class DaggerInfoModule{
    @Provides
    fun provideInfo():Info = Info("aboba")
}

@Singleton
@Component(modules = [DaggerInfoModule::class,BicycleModule::class])
interface DaggerInfoComponent : DaggerBicycleComponent {
    //fun inject(app:MainActivity)
    //fun getBicycleFactory():BicycleFactory
}