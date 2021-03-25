package kotlin_code.check

import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CheckHandler {

    var checkList = ArrayList<Payable>()
    val scan = Scanner(System.`in`)

    /**
     * Vang de keuze af in het menu
     */
    fun showAllCheckOptions() {

        while (true) {
            this.showAllChecks()
            this.printCheckMenu()
            val option = scan.nextLine().trim().toInt()

            //Option 1: Create a new check
            if (option == 1) {
                createNewCheck()
            }
            //Option 2: Remove a check
            else if (option == 2) {
                if (checkList.size == 0) {
                    println("There are no checks to remove.")
                    continue
                }
                else {
                    removeCheck()
                }
            }
            //Option 3: Add item to a check
            else if (option == 3) {
                addItemToCheck()
            }
            //Option 4: display a check
            else if (option == 4) {
                displayCheck()
            }
            //Option 5: Go back to the main menu
            else if (option == 5) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    private fun createNewCheck() {
        try {
            println("Enter the title of the check:")
            val checkTitle = scan.nextLine().trim()
            this.checkList.add(Check(checkTitle))
            println("Check with title $checkTitle is created.")
        } catch (e: Exception) {
            println("Check was not created.")
        }
    }


    private fun addItemToCheck() {

        if (checkList.isEmpty()) return

        println("Which check do you want to edit?")
        println("Enter the check number.")
        println("Enter 0 to cancel...")

        val checkToEdit = scan.nextLine().trim().toInt()

        //Cancel editting
        if (checkToEdit == 0) {
            println("No check was edited.")
        }

        //Does this number exist?
        if (checkToEdit > 0) {
            if (checkToEdit > checkList.size) {
                println("This check does not exist.")
            } else {
                println("What do you want to add to the check?")
                printAddableItemsList()
                val itemNumber = scan.nextLine().trim().toInt()
                val index = checkToEdit - 1
                val check = checkList[index]

                var newCheck: Payable? = null

                when (itemNumber) {
                    1 -> newCheck = Wine(check)
                    2 -> newCheck = Beer(check)
                    3 -> newCheck = Soda(check)
                    else -> {
                        print("This is not an option.")
                    }
                }
                if (newCheck != null) {
                    checkList[index] = newCheck
                }
            }
        }
    }

    private fun printAddableItemsList() {
        println("1. Wine")
        println("2. Beer")
        println("3. Soda")
    }

    private fun removeCheck() {
        if (checkList.isEmpty()) return
        println("Which check do you want to remove?")
        println("Press 0 to cancel removing...")

        val checkToRemove = scan.nextLine().trim().toInt()
        if (checkToRemove == 0) {
            println("No check was removed.")
        } else if (checkToRemove > 0) {
            if (checkToRemove > checkList.size) {
                println("This check does not exist.")
            } else {
                val indexToRemove = checkToRemove - 1
                val nameOfRemovedCheck = checkList[indexToRemove]
                if (nameOfRemovedCheck is Check) {
                    checkList.removeAt(indexToRemove)
                    println("The check with number $checkToRemove and name ${nameOfRemovedCheck.checkTitle} was removed.")
                }
            }
        }
    }

    /**
     * Returns a ordened list with all the check names
     */
    private fun showAllChecks() {
        if (checkList.size == 0) {
            println("There are currently no checks.")
        }
        if (checkList.size > 0) {
            println("List of all current checks.")
            var counter = 1
            checkList.forEach {
                println("$counter. ${it.getTitle()}")
                counter++
            }
        }
    }

    private fun printCheckMenu() {
        println("Options:")
        println("===============")
        println("1. Create new check")
        println("2. Remove check")
        println("3. Add item to check")
        println("4. Display check")
        println("5. Go back")
    }

    /**
     * Betaal en verwijder de check en krijg een overzicht
     */
    private fun displayCheck() {

        if(checkList.isEmpty()){
            return
        }

        println("Which check do you want to display?")

        val checkToDisplay = scan.nextLine().trim().toInt()
        //TODO: Checks inbouwen voor checknumber
        var index = checkToDisplay - 1
        val check = (checkList[index])

        println("Overview of check")
        println(check.getDescription())
        println("Total: ${check.getCost()}")
    }

}