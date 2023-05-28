// числа
import kotlin.random.Random

fun main() {
    var n: Int
    do {
    println("Введите число больше нуля")
        n = readln().toIntOrNull()?: 0
        if (n <= 0) println("Введите корректное число ")

    } while (n <= 0)

    val firstList = List(5){ Random.nextInt(-5,5) }
    val filter = firstList.filter { it > 0 }
    println(firstList)
    println(filter)
    val list2 = firstList.toMutableList()

    firstList.forEachIndexed {index, item ->
        if (item % 2 == 0) list2[index] *= 10
    }

    println(list2)
    println(list2.sumOf { it })


        // Авторизация

println("Часть 2")
val regDB = HashMap<String, String>()
println("Регистрация $n аккаунтов")
for (i in 0 until n ) {
    println("Введите логин для регистрации")
    val login: String = readln()
    println("Введите пароль для регистрации")
    val pass: String = readln()
    regDB[login] = pass
}
println(regDB)
println("$n пользователей успешно зарегистрировано")
println("Авторизация")
println("Введите логин")
val authLogin: String = readln()
val authLoginBoolean = regDB.keys.contains(authLogin)
if (!authLoginBoolean)
println("Неверный логин")
else {
    println("Введите пароль")
    val authPass: String = readln()
    val authPassBoolean = regDB.values.contains(authPass)
    if (!authPassBoolean) {
        println("Неверный пароль")
    } else {
        println("Добро пожаловать $authLogin")
    }
}
}



