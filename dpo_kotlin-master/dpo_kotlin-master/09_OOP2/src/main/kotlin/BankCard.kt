abstract class BankCard {
    open var balance:Double = 0.0











     open fun currentBalance(){
        println("Текущий баланс - $balance")
        return currentBalance()
    }



   abstract fun pay(): Boolean


}

