package kotlin_code.transaction

import java.time.LocalDateTime

class TransactionProxy(dateTime: LocalDateTime, amount: Double, description: String): GetTransactionInfo {
    var transaction: Transaction = Transaction(dateTime, amount, description)

    override fun getDateTime() {
        transaction.getDateTime()
    }

    override fun getAmount() {
        transaction.getAmount()
    }

    override fun getDescription() {
        transaction.getDescription()
    }

    override fun toString(): String {
        return transaction.toString()
    }
}