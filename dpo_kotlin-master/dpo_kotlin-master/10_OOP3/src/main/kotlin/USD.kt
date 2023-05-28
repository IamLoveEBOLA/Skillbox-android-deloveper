class USD : CurrencyConverter {
    override val currencyCode: String = "228"


var well : Double = 0.600
    override fun convertToRUB(RUB: Double): Double {
        well += RUB
        return well


    }
}