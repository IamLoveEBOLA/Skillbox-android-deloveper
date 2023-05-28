class AlphaBank(balance : Double) : CreditCard(balance) {


    override var balance: Double = 10000.0
    override var creditBalance = 10000.0
    override var cashback: Double = 0.0
    override var bonusPoints: Double = 0.0
    override var replenish = credtLimit - creditBalance
    override var limit: Double = creditBalance + balance

    val name = "Сергей Александрович Шпак"
    val nameBank = "АльфаБанк"

    fun getInfo() {
        println("Здравствуйте $name , Вас приветсвует $nameBank Ваш баланс : $balance и Доступный вам кредит $creditBalance ")

        return

    }

}