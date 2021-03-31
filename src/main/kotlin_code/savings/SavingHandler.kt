package main.kotlin_code.savings

import java.lang.Exception
import java.util.*
import kotlin.math.round

class SavingHandler(
    var savings: Savings = Savings.instance,
    var scan: Scanner = Scanner(System.`in`),
    private val savingDisplay: SavingDisplay = SavingDisplay(savings)
) {
    /**
     * Toon alle opties m.b.t. spaarpotjes
     */
    fun showAllSavingsOptions() {
        while (true) {
            printSavingsMenu()
            val option = scan.nextLine().trim().toInt()

            //Option 1: Show saving categories
            if (option == 1) {
                this.showCategories()
            }
            //Option 2: Add saving category
            else if (option == 2) {
                this.addCategory()
            }
            //Option 3: Add money
            else if (option == 3) {
                this.addMoney()
            }
            //Option 4: Withdraw money
            else if (option == 4) {
                this.withdrawMoney()
            }
            //Option 5: Go back to the main menu
            else if (option == 5) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    /**
     * Laat alle categorie spaarpotjes zien.
     */
    private fun showCategories() {
        displaySavings()
        println("Press enter to return.")
        while (true) {
            if (scan.nextLine() != null) break
        }
    }

    /**
     * Voeg een spaarpot categorie toe
     */
    private fun addCategory() {
        try {
            println("Enter the name of the category:")
            val categoryName = scan.nextLine().trim()

            println("Enter amount you want to add:")
            val input = scan.nextLine().trim()
            val startAmount = round(input.replace(',', '.').toDouble() * 100) / 100

            savings.createMoneyBox(categoryName, startAmount)
            println("Saving category with name $categoryName is created.")
        } catch (e: Exception) {
            println("Category was not created.")
        }
    }

    /**
     * Stop geld in een spaarpotje
     */
    private fun addMoney() {
        if (!savingDisplay.hasNoMoneyBoxes()) {

            try {
                println("To which box do you want to add money? press 0 to cancel")
                displaySavings()
                val categoryNumber = scan.nextLine().trim().toInt()
                if (categoryNumber == 0) {
                    return
                }
                if(savings.getMoneyBoxes().size < categoryNumber || categoryNumber < 0){
                    println("This moneybox does not exist. Operation cancelled.")
                    return
                }

                println("Enter the amount or press 0 te cancel")
                val amountInput = scan.nextLine().trim()
                if (amountInput == "0") {
                    return
                }
                val transactionAmount = round(amountInput.replace(',', '.').toDouble() * 100) / 100

                savings.addMoneyToMoneyBox(categoryNumber, transactionAmount)
                println("€$transactionAmount has been added to the box.")

            } catch (e: Exception) {
                println("Something went wrong. Returning to menu...")
                return
            }
        }
        else{
            println("There are no boxes.")
        }
    }

    /**
     * Haal geld uit een spaarpotje
     */
    private fun withdrawMoney() {
        if (!savingDisplay.hasNoMoneyBoxes()) {

            try {
                println("From which box do you want to withdraw money? press 0 to cancel")
                displaySavings()
                val categoryNumber = scan.nextLine().trim().toInt()
                if (categoryNumber == 0) {
                    return
                }
                if(savings.getMoneyBoxes().size < categoryNumber || categoryNumber < 0){
                    println("This moneybox does not exist. Operation cancelled.")
                    return
                }

                println("Enter the amount or press 0 te cancel")
                val amountInput = scan.nextLine().trim()
                if (amountInput == "0") {
                    return
                }
                val transactionAmount = round(amountInput.replace(',', '.').toDouble() * 100) / 100

                savings.takeMoneyFromMoneyBox(categoryNumber, transactionAmount)
                println("€$transactionAmount has been withdrawn from the box.")

            } catch (e: Exception) {
                println("Something went wrong. Returning to menu...")
                return
            }
        }
        else{
            println("There are no boxes.")
        }
    }

    /**
     * Toon alle spaarpotjes
     */
    fun displaySavings(){
        savingDisplay.display()
    }

    private fun printSavingsMenu() {
        println("Options:")
        println("===============")
        println("1. Show saving categories")
        println("2. Add saving category")
        println("3. Add money")
        println("4. Withdraw money")
        println("5. Go back")
        println("Enter your choice:")
    }
}