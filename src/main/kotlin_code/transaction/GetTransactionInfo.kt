package kotlin_code.transaction

import java.time.LocalDateTime

interface GetTransactionInfo {
    fun getDateTime()
    fun getAmount()
    fun getDescription()
    override fun toString(): String
}