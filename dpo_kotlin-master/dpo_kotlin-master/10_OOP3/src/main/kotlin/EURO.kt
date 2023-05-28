class EURO : CurrencyConverter {



    override val currencyCode: String = "140"


    var well : Double = 0.800
    override fun convertToRUB(RUB: Double): Double {
        well += RUB
        return well


    }
}
