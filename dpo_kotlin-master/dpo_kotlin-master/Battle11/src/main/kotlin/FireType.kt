sealed class FireType {
    object SingleShot: FireType()

    data class BurstShot(val burstSize: Int): FireType()
}
