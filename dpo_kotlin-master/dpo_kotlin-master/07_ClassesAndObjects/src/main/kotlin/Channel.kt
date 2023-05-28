import kotlin.collections.List

object Channels {
    internal val allChannels = listOf(
        "1st channel",
        "News",
        "Movies",
        "24/7",
        "Sport",
        "Nature",
        "National Geographic",
        "Culture",
        "Cinema",
        "TV Series",
        "Fishing",
        "Hunting",
        "Football 24",
        "Racing",
        "Formula 1",
        "Hockey 24"
    )

    fun getRandomChannels(amountOfChannels: Int): List<String> {
        val amountOfChannels = minOf(amountOfChannels, allChannels.size)
        return allChannels.shuffled().slice(0 until amountOfChannels)
    }
}
