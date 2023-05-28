open class Wheel (private var currentPressure:Double) {

    fun getPressure(pressure:Double) {

        if (pressure > 10.0 || pressure < 0.0) Except.IncorrectPressure()
        else currentPressure = pressure
    }


    fun checkPressure(){
        if (currentPressure < 1.6 || currentPressure > 0.0){
            Except.TooLowPressure()
        }
        else if (currentPressure in 1.6..2.5){
            println("Нормальный уровень давления")
        }
        else if (currentPressure in 2.5..10.0)
            Except.TooHighPressure()
    }
}