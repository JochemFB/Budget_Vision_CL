package kotlin_code.transaction

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Transaction(private val dateTime: LocalDateTime, private val amount: Double, private val description: String): GetTransactionInfo {
    private val sb = kotlin.text.StringBuilder()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")

    override fun getDateTime() {
        println("Transaction dateTime: ${dateTime.format(dateTimeFormatter)}")
    }

    override fun getAmount() {
        println("Transaction amount: ${String.format("%.2f", amount)}")
    }

    override fun getDescription() {
        println("Transaction description: $description")
    }

    override fun toString(): String {
        sb.append("DateTime: ${getDateTime()} ${System.getProperty("line.separator")} ")
        sb.append("Amount: ${getAmount()} ${System.getProperty("line.separator")} ")
        sb.append("Description: ${getDescription()} ${System.getProperty("line.separator")} ")
        return sb.toString()
    }
}