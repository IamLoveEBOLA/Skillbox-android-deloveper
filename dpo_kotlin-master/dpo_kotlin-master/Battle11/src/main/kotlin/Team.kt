class Team (val unit:String,teamSize: Int) {

       var teamList = mutableListOf<AbstractWarrior>()

       init {
           for (i in 1..teamSize) {
               when (true) {
                   30.chance() -> teamList.add(General())
                   50.chance() -> teamList.add(Captain())
                   else -> teamList.add(Soldier())
               }
           }
           printTeamInfo(attacker = null, victim = null)

       }

    fun printTeamInfo(attacker: AbstractWarrior?, victim: AbstractWarrior?) {
        println("\n     The \"$unit\" team:\n--------------------------")
        teamList.forEachIndexed { i, it ->
            print("${String.format("%2d", i + 1)}. ")
            when {
                it === attacker ->
                    println("\u001B[32m${String.format("%11s", it.nameUnit)}\u001B[0m " +
                            "${String.format("%6.1f", it.currencyHealth)} HP.")
                it === victim ->
                    println("\u001B[31m${String.format("%11s", it.nameUnit)} " +
                            "${String.format("%6.1f", it.currencyHealth)} HP.\u001B[0m")
                else ->
                    println("${String.format("%11s", it.nameUnit)} " +
                            "${String.format("%6.1f", it.currencyHealth)} HP.")
            }
        }
        println("--------------------------")
        println("${String.format("%15s", "Общее")} ${String.format("%6.1f", teamHP())} Очков здоровья.")
    }


       private fun teamHP(): Double {
           var overallHP = 0.0
           teamList.forEach { overallHP += it.currencyHealth }
           return overallHP
       }

}
