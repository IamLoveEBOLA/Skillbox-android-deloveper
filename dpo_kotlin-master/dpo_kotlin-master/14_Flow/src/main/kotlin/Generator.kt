import kotlinx.coroutines.flow.*


object Generator {
    fun sack(): Flow<Int> {
        return (1..90).shuffled().distinct().asFlow()
    }
}