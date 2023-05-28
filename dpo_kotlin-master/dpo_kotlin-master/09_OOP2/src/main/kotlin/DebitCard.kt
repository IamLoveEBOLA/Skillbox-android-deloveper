 open class DebitCard(balance: Double) : BankCard() {

     override var balance: Double = 0.0
     open var cashback: Double = 0.0
     open var bonusPoints: Double = 0.0
     open var bonusUP: Int = 0


     fun topUP() {
         println("Введите сумму пополнения")
         val charge = readln().toInt()
         this.balance += charge

         println("Пополнение успешно чумба вот твой баланс: $balance")
     }

     override fun pay(): Boolean {
         println("Введите сумму для оплаты")
         val price = readln().toIntOrNull()
         println("Оплатить: $price ?")
         if ((balance - price!!) > 0) {
             balance -= price
             bonusPoints += ((price / 100) * 1).toInt()
             if (price >= 5000) cashback += price / 20
             println("оплата успешна, списано : $price,")
             currentBalance()
             return true
         } else
             println("Недостаточно средств")
         currentBalance()
         return false
     }







     override fun currentBalance(){
         println("Текущий баланс - Ваш баланс: ${balance} , кэшбэк:  ${cashback}  , Бонусные баллы: ${bonusPoints}")
         return
     }



 }




