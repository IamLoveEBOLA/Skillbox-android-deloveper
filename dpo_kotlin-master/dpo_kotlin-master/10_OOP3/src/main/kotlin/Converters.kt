

object  Converters {
    val dollar = USD()
    val euro = EURO()
    val moneyFuture = CAPS()


    fun get(currencyCode: String): CurrencyConverter? {
        when (currencyCode) {
            dollar.currencyCode -> return dollar
            euro.currencyCode -> return euro
            moneyFuture.currencyCode -> return moneyFuture


            else -> return object : CurrencyConverter {
                override val currencyCode: String = "228"
                var well = 0.777
                override fun convertToRUB(RUB: Double): Double {
                    well += RUB
                    return well
                }
            }
        }
    }
}






