import kotlinx.coroutines.runBlocking

fun main(){
    val game = Game(colPlayers = 100)

    runBlocking {
        game.run()
    }
}