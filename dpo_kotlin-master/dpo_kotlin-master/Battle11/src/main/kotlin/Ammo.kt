import kotlin.random.Random

enum class Ammo(
    var damage: Int,
    private val criticalHitChance: Int,
    private val criticalDamageRatio: Double
) {
    PistolAmmo(50, 75, 3.0),
    RifleAmmo(60, 50, 2.5),
    MachinegunAmmo(25, 30, 0.9),
    RocketAmmo(9999, 100, 100.0),
    Palka(0, 0, 0.0);


    fun getCurrentDamage(): Double {
        var hitDamage = damage.toDouble()
        if (criticalHitChance.chance()) hitDamage = damage * criticalDamageRatio
        return hitDamage
    }


}

