fun main(){
    val Wheel = Wheel(2.0)

    try {
        Wheel.getPressure((readln().toDouble()))
        println(Wheel.checkPressure())


    }
    catch (E:Except.IncorrectPressure){
        println("Крит")
    }
    catch (E:Except.TooHighPressure){
        println("Высокий")
    }
    catch (E:Except.TooLowPressure){
        println("Низкое")

    }
    finally {
        println("Конец")
    }
}
