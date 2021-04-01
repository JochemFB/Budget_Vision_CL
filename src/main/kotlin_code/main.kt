import main.kotlin_code.check.CheckHandler
import main.kotlin_code.pubcrawl.PubcrawlHandler
import main.kotlin_code.savings.SavingHandler
import main.kotlin_code.transaction.TransactionHandler
import java.util.*

fun main() {

    val transaction = TransactionHandler()
    val check = CheckHandler()
    val savings = SavingHandler()
    val pubcrawl = PubcrawlHandler()

    while (true) {
        printMenu()

        val scan = Scanner(System.`in`)
        try {

            when (scan.nextLine().trim().toInt()) {
                1 -> {
                    transaction.showAllTransactionOptions()
                }
                2 -> {
                    check.showAllCheckOptions()
                }
                3 -> {
                    savings.showAllSavingsOptions()
                }
                4 -> {
                    pubcrawl.showAllPubcrawlOptions()
                }
                else -> {
                    println("This is not an option.")
                }
            }
        } catch (e: NumberFormatException) {
            println("Enter a valid number.")
            continue
        }
    }
}

private fun printMenu() {
    println("Main Menu:")
    println("--------------")
    println("1. Transactions")
    println("2. Checks")
    println("3. Savings")
    println("4. Pub Crawl")
    println("--------------")
    println("Enter your choice:")
}