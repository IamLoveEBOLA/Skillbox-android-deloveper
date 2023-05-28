fun main() {
    var alpha = AlphaBank(balance = 0.0)
   var sber = SberDebit(balance = 0.0)
     sber.getInfo()
     sber.topUP()
     sber.pay()


     alpha.getInfo()
     alpha.topUP()
     alpha.pay()
     alpha.creditMinus()
}






