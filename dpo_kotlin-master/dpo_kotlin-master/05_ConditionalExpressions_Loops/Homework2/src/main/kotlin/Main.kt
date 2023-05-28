// Через WHILE


fun main() {
    var i = 0
    var sum = 0
    while (i <= 0) {
        print("Введите число больше нуля: ")
        i = readLine()?.toIntOrNull() ?: 0
        sum += i
        i++
        if (i <= 0) {
            println("ERROR  -> Введите данные, согласно условию")
        } else {
            println("Ответ через цикл while -> ${fibWhile(i)}")

        }
    }
}

fun fibWhile(n: Int): Int {
    var num0 = 0
    var num1 = 1
    var fibonacci = 0

    if (n <= 1) return 0
    if (n == 2) return 1

    var i = 3
    while (i++ < n) {
        fibonacci = num0 + num1
        num0 = num1
        num1 = fibonacci

        if (i == n) {
            return fibonacci
        }
    }
    return fibonacci
}