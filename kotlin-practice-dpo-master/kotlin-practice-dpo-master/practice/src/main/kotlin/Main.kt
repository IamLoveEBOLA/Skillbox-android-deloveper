fun main() {
    println("Введете колличество номеров телефонов")
    val n = readln()?.toIntOrNull()?: return
    if(n <= 0) {
        println("Пожалуйста введете корректный номер телефона")
    }



    val listMutable = introductionNumber(n)
    val listMutableAllNumbers = listMutable.joinToString (separator = ",")
    val listMutable7Filter = listMutable.filter { it.startsWith("+7")}.joinToString(separator = ", ")

    val listMutableUniqueNumbers = listMutable.toSet()
    val sumListMutable = listMutable.sumOf { it.length }
    val numberAndName = mutableMapOf<String, String>()

    listMutable.forEach {
        println("Введите имя пользователя $it:")
        val name = readlnOrNull().toString()
        numberAndName[it] = name
    }
    println("Номера телефонов с пользователями")
    numberAndName.forEach { (key, value) -> println("Пользователь: $value. Номер телефона: $key") }


    println("Все номера : $listMutableAllNumbers")
    println("Начинается с +7 : $listMutable7Filter")
    println("Уникальные номера : ${listMutableUniqueNumbers.size}")
    println("Сумма всех длинн номеров телефонов : $sumListMutable")
}


fun introductionNumber (n:Int) = List(n){
    println("Введете номер телефона")
    readln()
}
