class Battle(teamSize: Int) {

    var team1 = Team("GDI", teamSize)
    var team2 = Team("NOD", teamSize)
    var isGameOver:Boolean = false


    private fun getBattleState(): BattleState {
        return if (team1.teamList.isEmpty()) {
            isGameOver = true
            BattleState.Team2Win(team1)
        } else if (team2.teamList.isEmpty()) {
            isGameOver = true
            BattleState.Team1Win(team1)
        } else BattleState.Progress(team1, team2)
    }

    fun clutch(team1: Team, team2: Team) {
        println("\nЮнит\"${team1.unit}\" команды атакует.")
        val attacker = team1.teamList.random()
        val victim = team2.teamList.random()
        attacker.attack(victim)
        team1.printTeamInfo(attacker, victim)
        team2.printTeamInfo(attacker, victim)
        val iterator = team2.teamList.iterator()
        while (iterator.hasNext()) {
            val it = iterator.next()
            if (!it.isAlive) {
                println("\nЮнит ${it.nameUnit} был\u001B[31m Убит †\u001B[0m из \"${team2.unit}\" команды.")
                iterator.remove()
            }
        }
        getBattleState()
    }
}

