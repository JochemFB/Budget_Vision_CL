import kotlin_code.check.CheckHandler
import kotlin_code.transaction.TransactionHandler
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {

    var checkHandler = CheckHandler()

    while (true) {
        printMenu()

        val scan = Scanner(System.`in`)
        try {
            val option = scan.nextLine().trim().toInt()



            if (option == 1) {
                println("Button 1 pressed");
            } else if (option == 2) {
                checkHandler.showAllCheckOptions()
            } else if (option == 3) {
                println("Button 3 pressed");
            } else if (option == 4) {
                println("Button 4 pressed");
            } else {
                println("This number is not an option.")
            }
        }
        catch (e : NumberFormatException){
            println("Enter a valid number.")
            continue
        }
    }
}

private fun printMenu() {
    println("Main Menu:");
    println("--------------");
    println("1. Show transactions");
    println("2. Show checks");
    println("3. Alter savings");
    println("4. Pub Crawl");
    println("--------------");
    println("Enter your choice:");
}