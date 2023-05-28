abstract class AbstractWarrior : Warrior

fun AbstractWarrior.attack(victim: AbstractWarrior) {
    var damage = 0.0
    try {
        for (item in this.weapon.toShoot()) {
            if (this.accuracy.chance() && !victim.chanceToDodge.chance()) damage += item.getCurrentDamage()
        }
    } catch (ex: NoAmmoException) {
        this.weapon.reload()
    }
    victim.currencyHealth = maxOf(0.0, (victim.currencyHealth - damage))
    victim.isAlive = victim.currencyHealth > 0.0
}





