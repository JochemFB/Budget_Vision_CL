import kotlin_code.transaction.TransactionHandler
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {

    while (true) {
        printMenu()

        val scan = Scanner(System.`in`)

        when (scan.nextLine().trim().toInt()) {
            1 -> {
                TransactionHandler()
            }
            2 -> {
                println("Button 2 pressed")
            }
            3 -> {
                println("Button 3 pressed")
            }
            4 -> {
                println("Button 4 pressed")
            }
            else -> {
                println("This is not an option.")
            }
        }
    }
}

private fun printMenu() {
    println("Main Menu:")
    println("--------------")
    println("1. Show transactions")
    println("2. Make check")
    println("3. Alter savings")
    println("4. Pub Crawl")
    println("--------------")
    println("Enter your choice:")
}