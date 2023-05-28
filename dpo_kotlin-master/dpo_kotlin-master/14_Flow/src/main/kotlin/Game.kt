import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Game(colPlayers: Int) {

    private val player: MutableList<Player> = mutableListOf()
    private val numberFromSack = Generator.sack()
    private val playerScopesPool = mutableListOf<Job>()

    suspend fun run() = coroutineScope {
        player.forEach { player ->
            val playerScope = launch {
                player.info()
                numberFromSack.collect { number ->
                    if (!player.checkNumbers(number)) {
                        println(
                            State.GameOver(
                                """
                             |Игрок ${player.name} - Победил!!
                             |Игра окончена!
                            """.trimIndent()
                            )
                        )
                        playerScopesPool.forEach { it.cancel() }
                    } else {
                        println(State.Status("Игрок ${player.name} зачеркивает поле на карте равно { $number }"))

                    }
                    delay(300)
                }
            }
            playerScopesPool.add(playerScope)

        }
        println(State.GameStart("Игра началась!"))
    }

    init {
        while (player.size < colPlayers) {
            val playerId = Random.nextInt(1, 999)
            player.add(Player(name = "Player#$playerId", Random.nextInt(1, 4)))
        }
    }
}