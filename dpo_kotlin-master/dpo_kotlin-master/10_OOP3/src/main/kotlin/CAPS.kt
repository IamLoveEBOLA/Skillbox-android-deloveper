class CAPS : CurrencyConverter {

    override val currencyCode: String = "150"


    var well : Double = 0.150
    override fun convertToRUB(RUB: Double): Double {
        well += RUB
        return well


    }
}
