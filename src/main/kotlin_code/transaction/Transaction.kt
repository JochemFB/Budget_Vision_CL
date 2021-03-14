package kotlin_code.transaction

import java.time.LocalDateTime

class Transaction(private val dateTime: LocalDateTime, private val amount: Double, private val description: String): GetTransactionInfo {
    val sb = kotlin.text.StringBuilder()

    override fun getDateTime() {
        println("Transaction dateTime: $dateTime")
    }

    override fun getAmount() {
        println("Transaction amount: $amount")
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