import kotlin.random.Random

class PlayerCard(rows: Int = ROWS, cols: Int = COLS) {

    private var numbers = generateSequence { Random.nextInt(1, 91) }
        .distinct()
        .take(15)
        .toSet()
        .chunked(5)

    private var card = mutableListOf<MutableList<Int>>()

    fun getCard(): MutableList<MutableList<Int>> {
        return card
    }

    init {
        for ((counter, row) in numbers.withIndex()) {
            card.add(row.toMutableList())
            while (card[counter].size < cols) {
                card[counter].add(0)
            }
            card[counter].shuffle()
        }
    }

    companion object {
        const val ROWS = 3
        const val COLS = 9
    }
}