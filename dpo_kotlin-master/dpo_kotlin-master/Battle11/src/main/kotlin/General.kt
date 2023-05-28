class General () : AbstractWarrior() {
    override val maxHealth = 450.0
    override var chanceToDodge = 60
    override val accuracy = 75
    override val weapon = Weapons.createPistol()
    override var currencyHealth = maxHealth
    override var nameUnit = "Генерал"
    override var isAlive =true
}



