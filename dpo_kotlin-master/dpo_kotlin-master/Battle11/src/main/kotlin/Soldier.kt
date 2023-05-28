class Soldier() : AbstractWarrior() {

    override var isAlive =true
    override val maxHealth = 200.0
    override var chanceToDodge = 25
    override val accuracy = 45
    override val weapon =Weapons.createRifle()
    override var currencyHealth = maxHealth
    override var nameUnit ="Стрелок"
}
