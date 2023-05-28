import java.util.*

object Weapons {
    fun createPistol(): AbstractWeapon {
        val anonymous = object : AbstractWeapon(
            fireType = FireType.SingleShot,
            maxAmmo = 1,
            clipAmmo = Stack(),
        ) {
            override fun createAmmo(): Ammo {
                return Ammo.PistolAmmo
            }
        }
        return anonymous
    }


    fun createRifle(): AbstractWeapon {
        val anonymous = object : AbstractWeapon(
            fireType = FireType.BurstShot(5),
            maxAmmo = 30,
            clipAmmo = Stack(),
        ) {
            override fun createAmmo(): Ammo {
                return Ammo.RifleAmmo
            }
        }
        return anonymous
    }


    fun createRocketLauncher(): AbstractWeapon {
        val anonymous = object : AbstractWeapon(
            fireType = FireType.SingleShot,
            maxAmmo = 1,
            clipAmmo = Stack()

        ) {
            override fun createAmmo(): Ammo {
                return Ammo.RocketAmmo
            }
        }
        return anonymous
    }
}




