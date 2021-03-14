package kotlin_code.transaction

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class TransactionHandler {
    /*
    Hier moet een ArrayList van TransactionProxies komen. Elke TransactionProxy heeft dan zijn eigen Transaction object.
    In deze klasse komen dus ook de add/remove etc. functies voor de ArrayList.
     */

    private val transactionProxies = ArrayList<TransactionProxy>()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")

    private var valid = false
    private lateinit var transactionDateTime: LocalDateTime

    init {
        while(true) {
            printCheckMenu()

            val scan = Scanner(System.`in`)
            val option = scan.nextLine().trim().toInt()

            //Option 1: Create new transaction
            if(option == 1) {
                while(!valid) {
                    println("Datum en tijd (dd-MM-yyyy HH:mm):")
                    valid = try {
                        transactionDateTime = LocalDateTime.parse(scan.nextLine().trim(), dateTimeFormatter)
                        true
                    } catch (e: DateTimeParseException) {
                        println("De ingevulde datum klopt niet. Probeer het opnieuw.")

                        false
                    }
                }

                println("Amount:")
                val transactionAmount = scan.nextLine().trim().toDouble()

                println("Description:")
                val transactionDescription = scan.nextLine().trim()

                try {
                    addTransaction(transactionDateTime, transactionAmount, transactionDescription)
                    println("Transaction added:")
                    println(transactionDateTime)
                    println(transactionAmount)
                    println(transactionDescription)
                } catch (e: Exception) {
                    println("Transaction could not be added")
                }
            }

            //Option 2: Remove a transaction
            else if(option == 2) {
                if(transactionProxies.size == 0) {
                    println("There are no checks to remove.")
                    continue
                }

                println("Enter the id of the transaction which you want to remove:")
                val transactionToRemove = scan.nextLine().trim().toInt()
                if(transactionToRemove == 0) {
                    println("This transaction could not be found.")
                } else if (transactionToRemove > 0) {
                    if (transactionToRemove > transactionProxies.size) {
                        println("This transaction could not be found.")
                    } else {
                        removeTransaction(transactionToRemove)
                    }
                }
            }

            //Option 3: Read a transaction by id
            else if(option == 3) {
                println("Enter the id of the transaction which you want to read:")
                val transactionToRead = scan.nextLine().trim().toInt()

                try {
                    try {
                        readTransaction(transactionToRead)
                    } catch (e: IndexOutOfBoundsException) {
                        println("This transaction could not be found.")
                    }
                } catch (e: Exception) {
                    println("Transaction could not be removed")
                }
            }

            //Option 4: Go back to the main menu
            else if (option == 4) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    private fun addTransaction(transactionDateTime: LocalDateTime, transactionAmount: Double, transactionDescription: String) {
        transactionProxies.add(TransactionProxy(transactionDateTime, transactionAmount, transactionDescription))
    }

    private fun removeTransaction(id: Int) {
        val indexToRemove = id - 1
        transactionProxies.removeAt(indexToRemove)
        println("The transaction with id $id was removed.")
    }

    private fun readTransaction(id: Int) {
        val indexToRead = id - 1
        transactionProxies[indexToRead].toString()
    }

    private fun printCheckMenu() {
        println("Options:")
        println("===============")
        println("1. Create new transaction")
        println("2. Remove transaction")
        println("3. Read transaction")
        println("4. Go back")
    }
}