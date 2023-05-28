import FireType.SingleShot
import FireType.BurstShot

abstract class AbstractWeapon(
    val maxAmmo: Int,
    val fireType: FireType,
    var clipAmmo: Stack<Ammo>
) {
    init {
        reload()
    }

    abstract fun createAmmo(): Ammo

    fun reload() {
        clipAmmo.clear()
        for (i in 1..maxAmmo) clipAmmo.push(createAmmo())
    }

    fun toShoot(): MutableList<Ammo> {
        val firedBullets = mutableListOf<Ammo>()
        val amountOfBullets: Int = when (fireType) {
            is SingleShot -> 1
            is BurstShot -> fireType.burstSize
        }
        if (amountOfBullets > clipAmmo.size()) throw NoAmmoException()
        else for (i in 1..amountOfBullets) {
            clipAmmo.pop()?.let { firedBullets.add(it) }
        }
        return firedBullets
    }
}







