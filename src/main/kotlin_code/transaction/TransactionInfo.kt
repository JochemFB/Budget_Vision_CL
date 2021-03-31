package main.kotlin_code.transaction

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionInfo(
    private val dateTime: LocalDateTime,
    private val amount: Double,
    private val description: String
) : Transaction {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")

    override fun getDateTime(): String {
        return dateTime.format(dateTimeFormatter)
    }

    override fun getAmount(): String {
        return String.format("%.2f", amount)
    }

    override fun getDescription(): String {
        return description
    }

    override fun toString(): String {
        val sb = kotlin.text.StringBuilder()
        sb.append("DateTime: ${getDateTime()} ${System.getProperty("line.separator")}")
        sb.append("Amount: ${getAmount()} ${System.getProperty("line.separator")}")
        sb.append("Description: ${getDescription()}")
        return sb.toString()
    }
}