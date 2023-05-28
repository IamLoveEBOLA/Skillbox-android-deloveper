import kotlin.random.Random

fun main() {
    println("Введите число для колличство юнитов:")
    var teamSize: Int?
    do {
        teamSize = readLine()?.toIntOrNull()
        if (teamSize == null) println("Некорректное значение, попробуете снова.")
    } while (teamSize == null)

    val battle = Battle(teamSize)
    var iteration = 1

    do {
        println("\n==========================")
        println("       Ход №${iteration}\n==========================")
        battle.clutch(battle.team1, battle.team2)
        if (!battle.isGameOver) {
            battle.clutch(battle.team2, battle.team1)
        }
        iteration += 1
    } while (!battle.isGameOver)
}


fun Int.chance(): Boolean {
    return this >= Random.nextInt(1, 101)
}

class NoAmmoException : Throwable("Недостаточно патронов,надо перезарядится.")



