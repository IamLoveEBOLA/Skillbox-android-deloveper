sealed class State(private val state: String) {
    data class GameOver(val state: String) : State(state)
    data class Status(val state: String) : State(state)
    data class GameStart(val state: String) : State(state)


}