sealed class Except {

    class TooHighPressure(massage: String = "Too High Pressure ") : Exception(massage){
        init {
            throw Exception(massage)
        }
    }





    class TooLowPressure(massage: String = "Too Low Pressure ") : Exception(massage){
        init {
            throw Exception(massage)
        }
    }


    class IncorrectPressure(massage: String = "Incorrect Pressure ") : Exception(massage){
        init {
            throw Exception(massage)
        }
    }
}