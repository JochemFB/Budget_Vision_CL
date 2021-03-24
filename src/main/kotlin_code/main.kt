import kotlin_code.check.CheckHandler
import kotlin_code.savings.SavingHandler
import kotlin_code.savings.Savings
import kotlin_code.transaction.TransactionHandler
import java.util.*

fun main(args: Array<String>) {

    val transaction = TransactionHandler()
    val checkHandler = CheckHandler()
    val savings = SavingHandler()

    while (true) {
        printMenu()

        val scan = Scanner(System.`in`)
        try {

            when (scan.nextLine().trim().toInt()) {
                1 -> {
                    transaction.showAllTransactionOptions()
                }
                2 -> {
                    checkHandler.showAllCheckOptions()
                }
                3 -> {
                    savings.showAllSavingsOptions()
                }
                4 -> {
                    println("Button 4 pressed")
                }
                else -> {
                    println("This is not an option.")
                }
            }
        }
        catch (e : NumberFormatException){
            println("Enter a valid number.")
            continue
        }
    }
}

private fun printMenu() {
    println("Main Menu:")
    println("--------------")
    println("1. Show transactions")
    println("2. Show checks")
    println("3. Alter savings")
    println("4. Pub Crawl")
    println("--------------")
    println("Enter your choice:")
}