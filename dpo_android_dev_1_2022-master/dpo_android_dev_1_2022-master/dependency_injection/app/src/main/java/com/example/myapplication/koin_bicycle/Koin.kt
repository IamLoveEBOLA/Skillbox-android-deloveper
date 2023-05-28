package com.example.myapplication.koin_bicycle

import com.example.myapplication.dagger_bicycle.BicycleFactory
import com.example.myapplication.dagger_bicycle.FrameFactory
import com.example.myapplication.dagger_bicycle.WheelDealer
import org.koin.dsl.module


val bicycleModule = module {
    single<WheelDealer> { provideWheelDealer() }
    single<FrameFactory> { provideFrameFactory() }
    single<BicycleFactory> { provideBicycleFactory(get() , get()) }
}

private fun provideWheelDealer(): WheelDealer = WheelDealer()
private fun provideFrameFactory(): FrameFactory = FrameFactory()
private fun provideBicycleFactory(
    wheelDealer: WheelDealer ,
    frameFactory: FrameFactory
): BicycleFactory {
    return BicycleFactory(wheelDealer , frameFactory)
}