package kotlin_code.transaction

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import kotlin.math.round

class TransactionHandler {
    /*
    TODO: Bij het intypen van quit of 0, ga terug naar main menu
    TODO: Check if the input for amount of valid (comma doesn't work as decimal. Dot does work as decimal)
    TODO: Als de gebruiker 0 invuld bij een van de 'add transaction' velden. Moet de gebruiker dan terug naar het main menu of naar het menu van de transactions?
    TODO: Transactions en spaarpotjes linken
     */

    private val transactionProxies = ArrayList<TransactionProxy>()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")

    private var validDateTime = false
    private lateinit var transactionDateTime: LocalDateTime

    init {
        while(true) {
            printTransactionMenu()

            val scan = Scanner(System.`in`)
            val option = scan.nextLine().trim().toInt()

            validDateTime = false

            //Option 1: Create new transaction
            if(option == 1) {
                println("Date and time (dd-MM-yyyy HH:mm) (enter 0 to cancel):")
                // Get user input
                var dateInput = scan.nextLine().trim()
                if(dateInput == "0") {
                    // Break out of loop to return to main menu
                    break
                }
                while(!validDateTime) {
                    // Check if dateInput is in the correct format (dd-MM-yyyy HH:mm)
                    validDateTime = try {
                        transactionDateTime = LocalDateTime.parse(dateInput, dateTimeFormatter)
                        true
                    } catch (e: DateTimeParseException) {
                        println("The date is incorrect. Please try again (enter 0 to cancel).")
                        // Get user input
                        dateInput = scan.nextLine().trim()
                        if(dateInput == "0") {
                            // Break out of loop to return to main menu
                            break
                        }

                        false
                    }
                }
                if(dateInput == "0") {
                    // Break out of loop to return to main menu
                    break
                }

                println("Amount (enter 0 to cancel):")
                // Get user input
                var amountInput = scan.nextLine().trim()
                if(amountInput == "0") {
                    // Break out of loop to return to main menu
                    break
                }
                var transactionAmount = round(amountInput.replace(',', '.').toDouble() * 100) / 100

                println("Description (enter 0 to cancel):")
                // Get user input
                var descriptionInput = scan.nextLine().trim()
                if(descriptionInput == "0") {
                    // Break out of loop to return to main menu
                    break
                }

                try {
                    // Add transactionProxy which includes a transaction
                    addTransaction(transactionDateTime, transactionAmount, descriptionInput)
                    // Save the ID of the latest inserted transactionProxy and print the latest transaction
                    val addedId = transactionProxies.size
                    println("Transaction added:")
                    readTransaction(addedId)
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
                for(i in transactionProxies.indices) {
                    println("Transaction id: ${i + 1}")
                    readTransaction(i+1)
                    println(System.getProperty("line.separator"))
                }
            }

            //Option 5: Go back to the main menu
            else if (option == 5) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    /**
     * Add a transaction based on the inputs given by the user
     * @param transactionDateTime A LocalDateTime defined by the user
     * @param transactionAmount A transaction amount defined by the user
     * @param transactionDescription A description amount defined by the user
     */
    private fun addTransaction(transactionDateTime: LocalDateTime, transactionAmount: Double, transactionDescription: String) {
        transactionProxies.add(TransactionProxy(transactionDateTime, transactionAmount, transactionDescription))
    }

    /**
     * Remove a transaction based on id
     * @param id Index of the transaction to remove
     */
    private fun removeTransaction(id: Int) {
        val indexToRemove = id - 1
        transactionProxies.removeAt(indexToRemove)
        println("The transaction with id $id was removed.")
    }

    /**
     * Read a transaction based on id
     * @param id Index of the transaction to read
     */
    private fun readTransaction(id: Int) {
        val indexToRead = id - 1
        transactionProxies[indexToRead].toString()
    }

    /**
     * Print the transaction sub-menu
     */
    private fun printTransactionMenu() {
        println("Options:")
        println("===============")
        println("1. Create new transaction")
        println("2. Remove transaction")
        println("3. Read transaction")
        println("4. List all transactions")
        println("5. Go back")
    }
}