import kotlin_code.transaction.TransactionHandler
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {

    println("Main Menu:");
    println("--------------");
    println("1. Show transactions");
    println("2. Make check");
    println("3. Alter savings");
    println("4. Pub Crawl");
    println("--------------");
    println("Enter your choice:");

    while (true) {

        val scan = Scanner(System.`in`)

        val option = scan.nextLine().trim().toInt()

        if (option == 1) {
            println("Button 1 pressed");
            TransactionHandler()
        } else if (option == 2) {
            println("Button 2 pressed");
        } else if (option == 3) {
            println("Button 3 pressed");
        } else if (option == 4) {
            println("Button 4 pressed");
        } else{
            println("This is not an option.")
        }
    }



}