package com.example.myapplication.dagger_bicycle

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


class Wheel(val vin: String) {
    override fun toString(): String {
        return vin
    }
}

class WheelDealer {
    var serialNumber: Int = 0
    fun getWheel(): Wheel {
        serialNumber++
        return Wheel(serialNumber.toString())
    }
}

class Frame(
    val serialNumber: String ,
    val color: String
) {
    override fun toString(): String {
        return "serialNumber:${serialNumber} color: ${color}"
    }
}

class FrameFactory {
    var serialNumber: Int = 0
    fun getFrame(color: String): Frame {
        serialNumber++
        return Frame(serialNumber.toString() , color)
    }
}

class Bicycle(
    val wheels: List<Wheel> ,
    val frame: Frame ,
    val logo: String
) {
    override fun toString(): String {
        return "wheel 1: ${wheels.first()} \n" +
                "wheel 2: ${wheels.last()} \n" +
                "frame: ${frame} \n" +
                "logo : ${logo} \n"
    }
}

class BicycleFactory(
    val wheelDealer: WheelDealer ,
    val frameFactory: FrameFactory
) {
    fun build(logo: String , color: String): Bicycle {
        return Bicycle(
            listOf(
                wheelDealer.getWheel() ,
                wheelDealer.getWheel()
            ),
                frameFactory.getFrame(color) ,
            logo
        )
    }
}
@Module
class BicycleModule {

    @Provides
    @Singleton
    @Named("wheelDealer")
    fun provideWheelDealer(): WheelDealer = WheelDealer()


    @Provides
    @Singleton
    @Named("frameFactory")
    fun provideFrameFactory(): FrameFactory = FrameFactory()

    @Provides
    fun provideBicycleFactory(
        @Named("wheelDealer") wheelDealer: WheelDealer,
        @Named("frameFactory") frameFactory: FrameFactory
    ) : BicycleFactory{
        return BicycleFactory(wheelDealer,frameFactory)
    }
}

@Singleton
@Component(modules = [BicycleModule::class])
interface DaggerBicycleComponent {
    //fun inject(app:MainActivity)
    fun getBicycleFactory(): BicycleFactory
}

