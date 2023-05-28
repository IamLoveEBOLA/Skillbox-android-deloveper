fun main() {
    println("Введите код валюты")
    val cod = readln()
    val sum = 5.0
    val converter = Converters.get(cod)



    if (converter != null){
        converter.convertToRUB(sum)
        println("$sum RUB = ${converter.convertToRUB(sum)} in cod ${converter.currencyCode}")

    }
    else println("Ошибка")
}
