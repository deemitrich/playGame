package connectfour

var rows = 0
var columns = 0

fun setTheBoard(firstName: String, secondName: String) {
    println("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)")

    val regex = Regex("""\s*\d+\s*[xX]\s*\d+\s*""")

    val enter = readLine()?.trim()
    if (enter.isNullOrEmpty()) {
        println("$firstName VS $secondName")
        println("6 X 7 board")
        rows = 6
        columns = 7
    } else {

        val valueResult = regex.matches(enter)

        if (valueResult) {

            val enterResult = enter.split("""\s*[xX]\s*""".toRegex())
            val rows_setTheBoard = enterResult[0].trim()
            val columns_setTheBoard = enterResult[1].trim()

            rows = rows_setTheBoard.toInt()
            columns = columns_setTheBoard.toInt()

            when {
                rows !in 5..9 -> {
                    println("Board rows should be from 5 to 9")
                    setTheBoard(firstName, secondName)
                }
                columns !in 5..9 -> {
                    println("Board columns should be from 5 to 9")
                    setTheBoard(firstName, secondName)
                }
                else -> {
                    println("$firstName VS $secondName")
                    println("$rows X $columns board")
                }
            }
        } else {
            println("Invalid input")
            setTheBoard(firstName, secondName)
        }
    }
}

fun seeTheBoardTest(placeGame: MutableList<MutableList<String>>) {

    for (i in 1..columns) {
        print(" $i")
    }
    println("")
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            print(placeGame[i][j])
        }
        println("|")
    }
    for (i in 1..columns * 2 + 1) {
        print("=")
    }
    println()
}


fun playGameTest(firstName: String, secondName: String, placeGame: MutableList<MutableList<String>>) {

    var columnFull = false

    println("$firstName's turn:")
    var firstMotion = readLine()
    while (firstMotion != null && firstMotion != "end") {
        val fi = firstMotion.toIntOrNull()
        if (fi == null || fi !in 1..columns) {
            println("The column number is out of range (1 and $columns)\n$firstName's turn:")
        } else {
            for (i in placeGame.lastIndex downTo 0) {
                if (placeGame[i][fi - 1] != "|o" && placeGame[i][fi - 1] != "|x") {
                    placeGame[i][fi - 1] = "|o"
                    break
                } else if (placeGame[i][fi - 1] != "|o" && placeGame[i][fi - 1] != "|x") {
                    placeGame[i - 1][fi - 1] = "|o"
                    break
                } else if (i == 0) {
                    columnFull = true
                }
            }
            if (columnFull) {
                println("Column $fi is full")
            } else seeTheBoardTest(placeGame)
            break
        }
        firstMotion = readLine()
    }
    if (firstMotion == "end") {
        print("Game over!")
        return
    }

    println("$secondName's turn: ")
    var secondMotion = readLine()
    while (secondMotion != null && secondMotion != "end") {
        val se = secondMotion.toIntOrNull()
        if (se == null || se !in 1..columns) {
            println("The column number is out of range (1 and $columns)\n$secondName's turn:")
        } else {
            for (i in placeGame.lastIndex downTo 0) {
                if (placeGame[i][se - 1] != "|o" && placeGame[i][se - 1] != "|x") {
                    placeGame[i][se - 1] = "|x"
                    break
                } else if (placeGame[i][se - 1] != "|o" && placeGame[i][se - 1] != "|x") {
                    placeGame[i - 1][se - 1] = "|x"
                    break
                } else if (i == 0) {
                    columnFull = true
                }
            }
            if (columnFull) {
                println("Column $se is full")
            } else seeTheBoardTest(placeGame)
            break
        }
        secondMotion = readLine()
    }
    if (secondMotion == "end") {
        print("Game over!")
        return
    }
    playGameTest(firstName, secondName, placeGame)
}


fun main() {
    println("Connect Four")
    println("First player's name:")
    val firstName = readln()
    println("Second player's name:")
    val secondName = readln()

    setTheBoard(firstName, secondName)

    val placeGame = MutableList(rows) { MutableList(columns) { "| " } }
    seeTheBoardTest(placeGame)
    playGameTest(firstName, secondName, placeGame)
}

