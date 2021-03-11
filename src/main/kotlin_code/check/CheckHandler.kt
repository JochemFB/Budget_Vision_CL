package kotlin_code.check

import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CheckHandler {

    var checkList = ArrayList<Check>()


    fun showCheckMenu() {
        this.clearScreen()
        this.showAllChecks()
        this.showAllCheckOptions()
    }


    private fun showAllCheckOptions() {
        println("Options:")
        println("===============")
        println("1. Create new check")
        println("2. Remove check")
        println("3. Edit check")
        println("4. Go back")

        while (true) {

            val scan = Scanner(System.`in`)

            val option = scan.nextLine().trim().toInt()

            if (option == 1) {
                println("Enter the title of the check:")
                val checkTitle = scan.nextLine().trim()

                try {
                    addCheck(checkTitle)
                    println("Check with title " + checkTitle + " is created.")
                }
                catch (e : Exception){
                    println("Check was not created.")
                }


            } else if (option == 2) {
                println("Button 2 pressed");
            } else if (option == 3) {
                println("Button 3 pressed");
            } else if (option == 4) {
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
            println("There are no checks yet.")
        }
        if (checkList.size > 0) {
            println("List of all current checks.")
            var counter = 1
            checkList.forEach() {
                println(counter.toString() + ". " + it.checkTitle)
                counter++
            }
        }
    }


    /**
     * Betaal en verwijder de check en krijg een overzicht
     */
    fun payCheck() {

    }


}