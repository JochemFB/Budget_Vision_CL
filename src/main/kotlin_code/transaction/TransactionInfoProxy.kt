package kotlin_code.transaction

import java.time.LocalDateTime

class TransactionInfoProxy(dateTime: LocalDateTime, amount: Double, description: String): Transaction {
    var transactionInfo: Transaction = TransactionInfo(dateTime, amount, description)

    override fun getDateTime(): String {
        return transactionInfo.getDateTime()
    }

    override fun getAmount(): String {
        return transactionInfo.getAmount()
    }

    override fun getDescription(): String {
        return transactionInfo.getDescription()
    }

    override fun toString(): String {
        return transactionInfo.toString()
    }
}