
fun secondPart(message: String){
    var second: String = message.drop(message.length/2)
    var reverseSecond = second.reversed()
    val shiftedStringSecond = reverseSecond.map {char -> char - 4}.joinToString("")
    println(shiftedStringSecond)
}

fun firstPart(message: String){
    var first: String = message.dropLast(message.length/2)
    val shiftedStringFirst = first.map {char -> char +1}.joinToString("")
    var sybmolS = shiftedStringFirst.replace(oldChar = '5', newChar = 's', )
    var sybmolU = sybmolS.replace(oldChar = '4', newChar = 'u',  )
    val shiftedStringFirst1 = sybmolU.map {char -> char -3}.joinToString("")
    var sybmolO = shiftedStringFirst1.replace(oldChar = '0', newChar = 'o',)
    println(sybmolO)
}
fun main () {
    println(firstPart(message = """F2p)v"y233{0->c}ttelciFc""" ))
    println(secondPart(message = """F2p)v"y233{0->c}ttelciFc"""))
}
