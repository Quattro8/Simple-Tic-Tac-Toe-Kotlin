package tictactoe
import kotlin.NumberFormatException

fun main() {
    // write your code here
    // val input = readln().chunked(3).map { it.toMutableList() }
    // if (input.size != 3) return

    val input = "_________".chunked(3).map { it.toMutableList() }
    renderBoard(input)


    var gameCompleted = false
    var currentSymbolX = true
    var enterSymbol: Char

    var xCount = 0
    var oCount = 0

    while (!gameCompleted) {
        var moveCompleted = false
        while (!moveCompleted) {
            try {
                val (row, column) = readln().split(" ").map { it.toInt() - 1 }
                if (row !in 0..2 || column !in 0..2) {
                    println("Coordinates should be from 1 to 3!")
                } else if (input[row][column] != '_') {
                    println("This cell is occupied! Choose another one!")
                } else {
                    when (currentSymbolX) {
                        true -> {
                            enterSymbol = 'X'
                            xCount++
                            currentSymbolX = currentSymbolX.not()
                        }
                        false -> {
                            enterSymbol = 'O'
                            oCount++
                            currentSymbolX = currentSymbolX.not()
                        }
                    }
                    input[row][column] = enterSymbol
                    renderBoard(input)
                    moveCompleted = true
                }
            } catch (error: NumberFormatException) {
                println("You should enter numbers!")
            } catch (error: IndexOutOfBoundsException) {
                println("Two numbers should be provided!")
            }
        }
        gameCompleted = checkResult(input, xCount, oCount)
        if (xCount + oCount == 9 && gameCompleted == false) {
            println("Draw")
            gameCompleted = true
        }
    }

    // println()
}

private fun renderBoard(input: List<MutableList<Char>>) {
    println("---------")
    input.forEach { figure ->
        println("| ${figure[0]} ${figure[1]} ${figure[2]} |")
    }
    println("---------")
}

private fun checkResult(input: List<MutableList<Char>>, xCount: Int, oCount: Int): Boolean {
    val combinations = listOf(
        listOf(input[0][0], input[0][1], input[0][2]), // horizontals
        listOf(input[1][0], input[1][1], input[1][2]), // horizontals
        listOf(input[2][0], input[2][1], input[2][2]), // horizontals
        listOf(input[0][0], input[1][0], input[2][0]), // verticals
        listOf(input[0][1], input[1][1], input[2][1]), // verticals
        listOf(input[0][2], input[1][2], input[2][2]), // verticals
        listOf(input[0][0], input[1][1], input[2][2]), // diagonals
        listOf(input[0][2], input[1][1], input[2][0])  // diagonals
    )

    if (listOf('X', 'X', 'X') in combinations && listOf('O', 'O', 'O') in combinations) {
        println("Impossible")
        return true
    } else if (listOf('X', 'X', 'X') in combinations) {
        println("X wins")
        return true
    } else if (listOf('O', 'O', 'O') in combinations) {
        println("O wins")
        return true
    }

    if (xCount - oCount >= 2 || oCount - xCount >= 2) {
        println("Not possible")
        return true
    } //"Impossible"

    combinations.forEach {
        return if (it.contains('_')) {
            false //"Game not finished"
        } else {
            false //"Draw"
        }
    }

    return false
}

