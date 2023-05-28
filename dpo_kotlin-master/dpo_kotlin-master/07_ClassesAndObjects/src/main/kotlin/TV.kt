class TV(val brand: String, val model: String, val diag: Double, val amountOfChannels: Int) {

    init {
        println("The $brand $model is activated.")
        println("The channels are setting up...")
        Thread.sleep(1000)
    }

    private val channelList = Channels.getRandomChannels(amountOfChannels)

    private var isTurnedOn: Boolean = false

    var currentChannel: Int = 0

    var currentVolume: Int = 0

    fun toTurnOn() {
        this.isTurnedOn = isTurnedOn == false
        if (isTurnedOn) println("TV is ON")
        else println("TV is OFF")
    }

    fun volumeUp(n: Int) {
        if (isTurnedOn) {
            currentVolume = minOf(maxVolume, currentVolume + n)
            println("Volume is $currentVolume")
        } else
            println("TV is OFF, you can`t change the volume.")
    }

    fun volumeDown(n: Int) {
        if (isTurnedOn) {
            currentVolume = maxOf(0, currentVolume - n)
            println("Volume is $currentVolume")
        } else
            println("TV is OFF, you can`t change the volume.")
    }

    fun chooseChannel(n: Int) {
        if (!isTurnedOn) toTurnOn()
        if (n > amountOfChannels) println("This TV support $amountOfChannels channels only.")
        else {
            currentChannel = n - 1  //Уменьшаем значение на 1 из-за индексации в списке с 0
            println("The '${channelList[currentChannel]}' channel is ON.")
        }
    }

    fun channelUp() {
        currentChannel += 1
        if (currentChannel > channelList.lastIndex) currentChannel = 0
        println("The '${channelList[currentChannel]}' channel is ON.")
    }

    fun channelDown() {
        currentChannel -= 1
        if (currentChannel < 0) currentChannel = channelList.lastIndex
        println("The '${channelList[currentChannel]}' channel is ON.")
    }

    fun getChannelList() {
        println("Channel list:")
        channelList.forEachIndexed { index, channel -> println("${index + 1} - $channel.") }
    }

    fun printInfo() {
        println("$brand $model $diag inches. ${minOf(amountOfChannels, Channels.allChannels.size)} channels supports.")
    }

    companion object {
        const val maxVolume = 100
    }
}