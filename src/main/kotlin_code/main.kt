package kotlin_code

import kotlin_code.check.*
import java.lang.Exception
import java.lang.NumberFormatException
import java.util.*

var checkHandler = CheckHandler()

fun main(args: Array<String>) {

    while (true) {

        println("Main Menu:");
        println("--------------");
        println("1. Show transactions");
        println("2. Show checks");
        println("3. Alter savings");
        println("4. Pub Crawl");
        println("--------------");
        println("Enter your choice:");

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