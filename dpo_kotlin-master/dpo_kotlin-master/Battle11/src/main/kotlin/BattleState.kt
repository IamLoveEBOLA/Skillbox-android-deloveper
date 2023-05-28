sealed class BattleState {
   class Progress(team1: Team,team2:Team) :BattleState(){
       init {
           println("\nБой Продожается." +
                   "\nВ \"${team1.unit}\" команде осталось ${team1.teamList.size} юнитов." +
                   "\nВ \"${team2.unit}\" команде осталось ${team2.teamList.size} юнитов." +
                   "\nСледующий ход.")
       }
   }



    class Team1Win(team: Team) : BattleState() {
        init {
            println(" \"${team.unit}\" Победил")
        }
    }

    class Team2Win(team: Team) : BattleState() {
        init {
            println(" \"${team.unit}\" Победил")
        }
    }
}

