import Action.Companion.resolveMyAction
import Action.Companion.resolveOpponentAction
import DuelOutcome.Companion.resolveDuel
import java.lang.IllegalStateException

fun main() {
    val testInput = readInput("Day02_input")

    part1(testInput)
    part2(testInput)
}

private fun part1(testInput: List<String>) {
    val result = testInput
        .sumOf {
            val opponentAction = it[0].resolveOpponentAction()
            val myAction = it[2].resolveMyAction()
            val roundScore = calculateRoundScore(opponentAction, myAction)
            roundScore
        }
    println(result)
}

private fun calculateRoundScore(opponentAction: Action, myAction: Action): Int {
    val scoreFromPick = myAction.weight
    val duelOutcome = myAction duel opponentAction
    return duelOutcome.score + scoreFromPick
}

private fun part2(testInput: List<String>) {
    val result = testInput
        .sumOf {
            val opponentAction = it[0].resolveOpponentAction()
            val duelOutcome = it[2].resolveDuel()
            val roundScore = calculateRoundScore(opponentAction, duelOutcome)
            roundScore
        }
    println(result)
}

private fun calculateRoundScore(opponentAction: Action, duelOutcome: DuelOutcome): Int {
    val myAction = Action.values().first { it duel opponentAction == duelOutcome }
    val scoreFromPick = myAction.weight
    return duelOutcome.score + scoreFromPick
}

enum class DuelOutcome(val code: Char, val score: Int) {
    Win('Z', 6),
    Draw('Y', 3),
    Lose('X', 0),
    ;

    companion object {
        fun Char.resolveDuel(): DuelOutcome {
            return DuelOutcome.values().first { it.code == this }
        }
    }
}

private enum class Action(val opponentCode: Char, val myCode: Char, val weight: Int) {
    Rock('A', 'X', 1),
    Paper('B', 'Y', 2),
    Scissors('C', 'Z', 3),
    ;

    infix fun duel(opponent: Action): DuelOutcome {
        if (this == opponent) return DuelOutcome.Draw
        return when (ordinal - opponent.ordinal) {
            1 -> DuelOutcome.Win
            -1 -> DuelOutcome.Lose
            2 -> DuelOutcome.Lose
            -2 -> DuelOutcome.Win
            else -> throw IllegalStateException()
        }
    }

    companion object {
        fun Char.resolveOpponentAction(): Action {
            return Action.values().first { it.opponentCode == this }
        }

        fun Char.resolveMyAction(): Action {
            return Action.values().first { it.myCode == this }
        }
    }
}