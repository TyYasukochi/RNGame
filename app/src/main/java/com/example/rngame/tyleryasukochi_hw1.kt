package com.example.rngame

//implemented Bonus 1 (line 26) *******
//implemented Bonus 2 (lines 41-52) *******


fun printRules() {
    //rules
    println("Rules: 10 random numbers between 0 and 999 will be generated.\n" +
            "Your goal is to place the randomly generated numbers in ascending order\n" +
            "with rank 1 being the lowest and rank 10 being the highest number.\n\n" +
            "You must rank each number as it is generated and you cannot change the ranks after being placed.\n\n" +
            "Numbers can only be placed in an unranked spot and between numbers above/below its value.\n\n" +
            "In the example below, the randomly generated number (576) MUST be placed in rank 3:\n\n" +
            "Remaining placements:9\n" +
            "Generated number: 576\n" +
            "Rank 1: - \n"+
            "Rank 2: - \n"+
            "Rank 3: - \n\n"+
            "Game is over when either:\n" +
            "a) 10 randomly generated numbers have been placed correctly\n" +
            "b) there are no more valid ranks to place the last randomly generated number\n\n" +
            "GOOD LUCK!\n-----------------------------------\n")
}

fun playagain(){
    //function to ask the user if they want to play again after loss/quit
    print("Would you like to play again? |y/n| ")
    val choice = readln()
    if (choice == "y"){
        //if yes, restarts game
        main()
    } else {
        //if no then exits
        return
    }
}

fun main(){
    printRules()

    //Creates an array that holds 10 ints for the Ranks and fills it with -1 to represent empty space
    val Ranks = Array(10) { -1 }

    print("What range would you like to use? ex.Input |0 999|: ")
    val range = readln()

    //game continues as long as there are empty spaces
while (Ranks.contains(-1)) {

    //extracts the numbers for range from the users input
    val numbers = range.split(" ")
    val number1 = numbers[0].toInt()
    val number2 = numbers[1].toInt()

    println("-----------------------------------")
    println("You are playing in range: $number1-$number2")

    //generates a random number between the two inputs
    val randnum = (number1..number2).random()

    //amount of placements is equal to amount of empty space in array Rank
    var placements = Ranks.count { it == -1 }
    println("Remaining Placements: $placements")

    println("Generated number: $randnum")

    //iterates through array and replaces all -1 with "-", then prints it
    for (i in Ranks.indices) {
        val displayValue = if (Ranks[i] == -1) "-" else Ranks[i].toString()
        println("Rank ${i + 1}: $displayValue")
    }

    //prompts the user for choice in ranking, then converts input to integer
    print("Choose a ranking (1-10) or 'q' to quit: ")
    val choice: String? = readLine()
    if (choice == "q") {
        print("Better luck next time! Only $placements numbers left!")
        break
    }



    //force converts because we know the input isnt bool
    val intchoice: Int = choice!!.toInt()

    //if the input is valid
    if (intchoice in 1..10) {

        //rank position is = input -1 because first value in array is held at 0
        val ranknum = intchoice - 1

        //when there are open spots
        if (Ranks[ranknum] == -1) {

            //establishes conditions for a valid ranking
            val valid = when {
                ranknum == 0 -> true
                ranknum > 0 && Ranks[ranknum - 1] != -1 && randnum < Ranks[ranknum - 1] -> false
                ranknum < 9 && Ranks[ranknum + 1] != -1 && randnum > Ranks[ranknum + 1] -> false
                else -> true
            }

            //if valid, the random number is assigned to the corresponding position in array based on user input
            if (valid) {
                Ranks[ranknum] = randnum

                //if not valid then prompts user the input was invalid
                } else {
                println("*******|   INVALID PLACEMENT: YOU MUST PLACE THE NUMBERS IN ASCENDING ORDER   |*******")
                }

        //corresponding else statement for the if that checks open spots
        } else {
            println("*******|   RANK ${intchoice} IS ALREADY FILLED   |*******")
        }
        //corresponding else statement for the if that checks for valid input
    } else {
        println("*******|   INVALID INPUT: PLEASE SELECT (1-10)   |*******")
    }

    //when all spots are filled you win
    if (!Ranks.contains(-1)){
        println("YOU WON!")
        break
    }
}
    //whenever the loop gets broken, ie. user quits, they get prompted to play again.
    playagain()
}



