package kotlin_code.savings

import kotlin_code.check.Check
import java.lang.Exception
import java.util.*
import kotlin.math.round

class SavingHandler(var savings: Savings = Savings(), var scan: Scanner = Scanner(System.`in`), val savingDisplay: SavingDisplay = SavingDisplay(savings)) {

    fun showAllSavingsOptions() {
        while (true) {
            printSavingsMenu()
            val option = scan.nextLine().trim().toInt()

            //Option 1: Show saving categories
            if (option == 1) {
                savingDisplay.display()
                println("Press enter to return.")
                while (true) {
                    if (scan.nextLine() != null) break
                }
            }
            //Option 2: Add saving category
            else if (option == 2) {
                try {
                    println("Enter the name of the category:")
                    val categoryName = scan.nextLine().trim()

                    println("Enter amount you want to add:")
                    val input = scan.nextLine().trim()
                    val startAmount = round(input.replace(',','.').toDouble() * 100) / 100

                    savings.createMoneyBox(categoryName,startAmount)
                    println("Saving category with name $categoryName is created.")
                } catch (e: Exception) {
                    println("Category was not created.")
                }
            }
            //Option 3: Add money
            else if (option == 3) {

            }
            //Option 4: Withdraw money
            else if (option == 4) {

            }
            //Option 5: Go back to the main menu
            else if (option == 5) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    fun printSavingsMenu() {
        println("Options:")
        println("===============")
        println("1. Show saving categories")
        println("2. Add saving category")
        println("3. Add money")
        println("4. Withdraw money")
        println("5. Go back")
    }
}