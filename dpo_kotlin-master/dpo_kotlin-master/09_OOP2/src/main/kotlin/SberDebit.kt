 class SberDebit(balance: Double) : DebitCard(balance) {



     override var balance: Double = 55000.0
     override var cashback: Double = 0.0
     override var bonusPoints: Double = 0.0
     val name = "Алексей Иванович Лютый"
     val nameBank = "СберБанк"




     fun getInfo() {
           println("Здравствуйте $name , Вас приветсвует $nameBank Ваш баланс : $balance")
           return
      }








 }











