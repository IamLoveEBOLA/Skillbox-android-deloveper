open class CreditCard( balance: Double) : BankCard() {

    override var balance: Double = 10000.0
    open var creditBalance: Double = 10000.0
    open var cashback: Double = 0.0
    open var bonusPoints: Double = 0.0
    var credtLimit: Double = 15000.0

    open var replenish: Double = credtLimit - creditBalance
        get() = credtLimit - creditBalance
    open var limit: Double = balance + creditBalance
        get() = balance + creditBalance


    fun topUP() {
        println("Введите сумму пополнения")
        var charge: Double = readln().toDouble()
        this.balance += charge
        println("Пополнение успешно")
        currentBalance()
    }


    override fun pay(): Boolean {
        println("Введите сумму для оплаты")
        var price: Double = readln().toDouble()
        if (balance >= price) {
            balance -= price
            println("Оплата была произведена из собственных средств ")
            currentBalance()
            return true
        } else {
            if ((balance - price + creditBalance) * (-1) >= credtLimit) {
                println("Оплата не была произведена, не хватает средств")
                currentBalance()
                return false
            } else {
                balance -= price
                creditBalance += balance
                balance = 0.0
                println("Оплата успешна из собственных и кредитных средств")
                currentBalance()
                return true
            }
        }

    }


    fun creditMinus() {
        println("Что бы погасить кредит введите сумму")

        val n: Double = readln().toDouble()
        val w: Double = creditBalance + n

        if (w < credtLimit) {
            creditBalance = w
            println("Спасибо кредит погашен")
            currentBalance()

        }
        if (w >= credtLimit) {

            val d: Double = w - credtLimit

            if (d == 0.0) {
                creditBalance = w


            } else {
                creditBalance = (w - d)
                balance += d
                println("Спасибо кредит погашен, была сумма больше кредита, остаток был пополнен в баланс")
                currentBalance()
            }
        }


    }


    override fun currentBalance() {
        println(
            """
                Кредитные средства: $creditBalance Ваш кредитный лимит: $credtLimit
                Собственные средства: $balance
            """.trimIndent()
        )
    }

}






