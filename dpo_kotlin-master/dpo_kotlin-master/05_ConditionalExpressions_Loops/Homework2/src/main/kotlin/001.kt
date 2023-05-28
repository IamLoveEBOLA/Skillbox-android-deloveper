
// Через FOR

fun main() {
    println("Введите число: ")
    var n = readLine()!!.toIntOrNull()?: return
    if (0 > n) {
        println("Введите корректное число")
        n = readLine()?.toIntOrNull()?: return
    }

    println(calculate(n))
}
fun calculate (n: Int){
    var num0 = 0
    var num1 = 1
    var fibonacci = 0

    for (i in 2 ..n){

        fibonacci = num0 + num1
        num0 = num1
        num1 = fibonacci
    }
    println(fibonacci)
}