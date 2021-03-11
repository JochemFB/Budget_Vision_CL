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
                }
                catch (e : Exception){
                    println("Check was not created.")
                }
            }
            //Option 2: Remove a check
            else if (option == 2) {

                if (checkList.size == 0){
                    println("There are no checks to remove.")
                    continue
                }

                println("Which check do you want to remove?")
                println("Press 0 to cancel removing...")

                val checkToRemove = scan.nextLine().trim().toInt()
                if (checkToRemove == 0){
                    println("No check was removed.")
                }
                else if(checkToRemove > 0){
                    if (checkToRemove > checkList.size){
                        println("This check does not exist.")
                    }
                    else{
                        val index = checkToRemove - 1
                        val nameOfRemovedCheck = checkList[index].checkTitle
                        checkList.removeAt(index)
                        println("The check with number $checkToRemove and name $nameOfRemovedCheck was removed.")
                    }
                }
            }
            //Option 3: Edit a check
            else if (option == 3) {
                println("Button 3 pressed");




            }
            //Option 4: Go back to the main menu
            else if (option == 4) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    fun clearScreen() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }

    fun addCheck(title: String) {
        checkList.add(Check(title))
    }

    fun removeCheck(index: Int) {
        checkList.removeAt(index - 1)
    }

    fun showAllChecks() {
        if (checkList.size == 0) {
            println("There are currently no checks.")
        }
        if (checkList.size > 0) {
            println("List of all current checks.")
            var counter = 1
            checkList.forEach() {
                println(counter.toString() + ". " + it.checkTitle)
                counter++
            }
            println("\n")
        }
    }

    fun printCheckMenu(){
        println("Options:")
        println("===============")
        println("1. Create new check")
        println("2. Remove check")
        println("3. Edit check")
        println("4. Go back")
    }

    /**
     * Betaal en verwijder de check en krijg een overzicht
     */
    fun payCheck() {

    }


}