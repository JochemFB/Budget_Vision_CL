package kotlin_code.check

import kotlin_code.MenuHandler
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CheckHandler {

    var checkList = ArrayList<Check>()

    fun showAllCheckOptions() {

        while (true) {
            this.showAllChecks()
            this.printCheckMenu()
            val scan = Scanner(System.`in`)
            val option = scan.nextLine().trim().toInt()

            //Option 1: Create a new check
            if (option == 1) {
                println("Enter the title of the check:")
                val checkTitle = scan.nextLine().trim()

                try {
                    addCheck(checkTitle)
                    println("Check with title $checkTitle is created.")
                } catch (e: Exception) {
                    println("Check was not created.")
                }
            }
            //Option 2: Remove a check
            else if (option == 2) {

                if (checkList.size == 0) {
                    println("There are no checks to remove.")
                    continue
                }

                println("Which check do you want to remove?")
                println("Press 0 to cancel removing...")

                val checkToRemove = scan.nextLine().trim().toInt()
                if (checkToRemove == 0) {
                    println("No check was removed.")
                } else if (checkToRemove > 0) {
                    if (checkToRemove > checkList.size) {
                        println("This check does not exist.")
                    } else {
                        removeCheck(checkToRemove)
                    }
                }
            }
            //Option 3: Edit a check
            else if (option == 3) {
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
                        println("Options")
                        println("Enter 1 to add an item to the check.")
                        println("Enter 2 to remove an item from the check.")
                        val editOption = scan.nextLine().trim().toInt()

                        //Add item to check
                        if (editOption == 1) {
                            println("What do you want to add to the check?")
                            printAddableItemsList()
                            val itemToAdd = scan.nextLine().trim().toInt()

                            addItemToCheck(itemToAdd, checkToEdit)

                        } else if (editOption == 2) {

                        } else {
                            println("This is not an option.")
                        }

                        //Remove item from check

                    }
                }


            } else if (option == 4) {
                println("Which check do you want to display?")

                val checkToDisplay = scan.nextLine().trim().toInt()
                //TODO: Checks inbouwen voor checknumber
                var index = checkToDisplay - 1
                displayCheck(checkList[index])


            }
            //Option 5: Go back to the main menu
            else if (option == 5) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }


    fun addCheck(title: String) {
        checkList.add(Check(title))
    }

    fun addItemToCheck(itemNumber: Int, checkNumber: Int) {

        val index = checkNumber-1
        var check = checkList[index]

        when (itemNumber) {
            1 -> Wine(check)
            2 -> Beer(check)
            3 -> Soda(check)
            else -> {
                print("This is not an option.")
            }
        }
        checkList.set(index, check)

    }

    fun printAddableItemsList() {
        println("1. Wine")
        println("2. Beer")
        println("3. Soda")
    }

    fun removeCheck(checkIndex: Int) {

        val indexToRemove = checkIndex - 1
        val nameOfRemovedCheck = checkList[indexToRemove].checkTitle
        checkList.removeAt(indexToRemove)
        println("The check with number $checkIndex and name $nameOfRemovedCheck was removed.")


    }

    fun showAllChecks() {
        if (checkList.size == 0) {
            println("There are currently no checks.")
        }
        if (checkList.size > 0) {
            println("List of all current checks.")
            var counter = 1
            checkList.forEach {
                println(counter.toString() + ". " + it.checkTitle)
                counter++
            }
            println("\n")
        }
    }

    fun printCheckMenu() {
        println("Options:")
        println("===============")
        println("1. Create new check")
        println("2. Remove check")
        println("3. Edit check")
        println("4. Display check")
        println("5. Go back")
    }

    /**
     * Betaal en verwijder de check en krijg een overzicht
     */
    fun displayCheck(check: Check) {
        println("Overview of check")
        println("${check.getDescription()}")
        println("Total: ${check.getCost()}")
    }

}