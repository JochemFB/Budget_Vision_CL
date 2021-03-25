package kotlin_code.transaction

import kotlin_code.savings.Savings
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import kotlin.math.round

class TransactionHandler {
    /*
    TODO: Bij het intypen van quit of 0, ga terug naar main menu
    TODO: Als de gebruiker 0 invuld bij een van de 'add transaction' velden. Moet de gebruiker dan terug naar het main menu of naar het menu van de transactions?
    TODO: Transactions en spaarpotjes linken
    TODO: date slashes or - and time optional
     */

    private val transactionInfoProxies = ArrayList<TransactionInfoProxy>()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm")

    private var validDateTime = false
    private lateinit var transactionDateTime: LocalDateTime

    private val scan = Scanner(System.`in`)

    fun showAllTransactionOptions() {
        while (true) {
            printTransactionMenu()
            val option = scan.nextLine().trim().toInt()

            validDateTime = false

            //Option 1: Create new transaction
            if (option == 1) {
                if (addTransaction()) break
            }

            //Option 2: Remove a transaction
            else if (option == 2) {
                removeTransaction()
            }

            //Option 3: Read a transaction by id
            else if (option == 3) {
                listTransaction()
            }

            //Option 4: Print all transactions
            else if (option == 4) {
                listAllTransactions()
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
     * List all transactions
     */
    private fun listAllTransactions() {
        if (transactionInfoProxies.size == 0) {
            println("There are no transactions to list.")
            return
        }

        for (i in transactionInfoProxies.indices) {
            println("Transaction id: ${i + 1}")
            readTransaction(i + 1)
            println(System.getProperty("line.separator"))
        }
    }

    /**
     * List a single transaction
     */
    private fun listTransaction() {
        println("Enter the id of the transaction which you want to read:")
        val transactionToRead = scan.nextLine().trim().toInt()

        try {
            try {
                readTransaction(transactionToRead)
            } catch (e: IndexOutOfBoundsException) {
                println("This transaction could not be found.")
            }
        } catch (e: Exception) {
            println("Transaction could not be removed.")
        }
    }

    /**
     * Remove a transaction based on id
     */
    private fun removeTransaction() {
        if (transactionInfoProxies.size == 0) {
            println("There are no transactions to remove.")
            return
        }

        println("Enter the id of the transaction which you want to remove:")
        val transactionToRemove = scan.nextLine().trim().toInt()
        if (transactionToRemove == 0) {
            println("This transaction could not be found.")
        } else if (transactionToRemove > 0) {
            if (transactionToRemove > transactionInfoProxies.size) {
                println("This transaction could not be found.")
            } else {
                val indexToRemove = transactionToRemove - 1
                transactionInfoProxies.removeAt(indexToRemove)
                println("The transaction with id $transactionToRemove was removed.")
            }
        }
    }

    /**
     * Add a transaction based on the inputs given by the user
     */
    private fun addTransaction(): Boolean {
        println("Date and time (d-M-yyyy HH:mm) (enter 0 to cancel):")
        // Get user input
        var dateInput = scan.nextLine().trim()
        if (dateInput == "0") {
            // Break out of loop to return to main menu
            return true
        }
        while (!validDateTime) {
            // Check if dateInput is in the correct format (dd-MM-yyyy HH:mm)
            validDateTime = try {
                transactionDateTime = LocalDateTime.parse(dateInput, dateTimeFormatter)
                true
            } catch (e: DateTimeParseException) {
                println("The date is incorrect. Please try again (enter 0 to cancel).")
                // Get user input
                dateInput = scan.nextLine().trim()
                if (dateInput == "0") {
                    // Break out of loop to return to main menu
                    break
                }

                false
            }
        }
        if (dateInput == "0") {
            // Break out of loop to return to main menu
            return true
        }

        println("Amount (enter 0 to cancel):")
        // Get user input
        var amountInput = scan.nextLine().trim()
        if (amountInput == "0") {
            // Break out of loop to return to main menu
            return true
        }
        var transactionAmount = round(amountInput.replace(',', '.').toDouble() * 100) / 100

        println("Description (enter 0 to cancel):")
        // Get user input
        var descriptionInput = scan.nextLine().trim()
        if (descriptionInput == "0") {
            // Break out of loop to return to main menu
            return true
        }

        val savings = Savings.instance
        if (savings.getMoneyBoxes().size > 0) {
            while (true) {
                println("Do you want to add this transaction to a saving box? Type 'y' or 'n'")
                // Get user input
                val categoryInput = scan.nextLine().trim().toLowerCase()
                if (categoryInput == "y") {

                    println("Enter the number of the box to add the transaction to.")
                    var counter = 1
                    savings.getMoneyBoxes().forEach() {
                        println("$counter. ${it.getCategory()}: €${it.getAmount()}")
                        counter++
                    }

                    try {
                        var moneyBoxNumber = scan.nextLine().trim().toInt()
                        savings.takeMoneyFromMoneyBox(moneyBoxNumber, transactionAmount)


                    } catch (e: Exception) {
                        println("An error occured.")
                    }

                    break
                }
                else if(categoryInput == "n"){
                    break
                }
                println("This is not a valid option. Try again.")

            }
        }
        try {
            // Add transactionProxy which includes a transaction
            transactionInfoProxies.add(TransactionInfoProxy(transactionDateTime, transactionAmount, descriptionInput))
            // Save the ID of the latest inserted transactionProxy and print the latest transaction
            val addedId = transactionInfoProxies.size
            println("Transaction added:")
            readTransaction(addedId)
        } catch (e: Exception) {
            println("Transaction could not be added")
        }
        return false
    }

    /**
     * Read a transaction based on id
     * @param id Index of the transaction to read
     */
    private fun readTransaction(id: Int) {
        val indexToRead = id - 1
        println(transactionInfoProxies[indexToRead].toString())
    }

    /**
     * Print the transaction sub-menu
     */
    private fun printTransactionMenu() {
        println("Options:")
        println("===============")
        println("1. Add transaction")
        println("2. Remove transaction")
        println("3. Read transaction by ID")
        println("4. List all transactions")
        println("5. Go back")
    }
}