class Captain() : AbstractWarrior(){
    override val maxHealth = 300.0
    override var chanceToDodge = 25
    override val accuracy = 55
    override val weapon =Weapons.createRocketLauncher()
    override var currencyHealth = maxHealth
    override var nameUnit ="Ракетчик"
    override var isAlive =true
}
