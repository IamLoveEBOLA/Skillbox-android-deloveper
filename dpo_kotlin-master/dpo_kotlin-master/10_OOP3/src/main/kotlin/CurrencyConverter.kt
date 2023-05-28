interface  CurrencyConverter {

    val currencyCode: String

    fun convertToRUB(value: Double): Double


}