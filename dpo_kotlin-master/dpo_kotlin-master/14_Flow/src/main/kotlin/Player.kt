import java.util.Collections.shuffle

class Player(val name: String, colCards: Int) {

    private var playerCard = mutableListOf<MutableList<MutableList<Int>>>()


    fun info() {
        println(cardOrCards())

        for (playCard in playerCard) {
            println("------------------")
            for (row in playerCard) println(row)
        }
    }

    fun checkNumbers(number: Int): Boolean {
        playerCard.forEach { card ->
            card.forEach { row ->
                if (row.sum() == 0) {
                    return false
                }
                for ((index, num) in row.withIndex()) {
                    if (number == num) {
                        row[index] = 0
                    }
                }
            }
        }
        return true
    }

    private fun cardOrCards(): String {
        return if (playerCard.size > 1) {
            "\n${playerCard.size} карточки игрока $name "
        } else "\n${playerCard.size} карточка игрока $name "
    }

    init {
        for (card in 1..colCards) {
            playerCard.add(PlayerCard().getCard())
        }
    }

}



