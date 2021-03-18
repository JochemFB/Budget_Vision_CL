package kotlin_code.savings

import kotlin_code.transaction.Transaction


interface Savable {

    var transactionList : ArrayList<Transaction>

    fun addMoney(money: Double)

    fun takeMoney(money: Double)

    fun getCategory(): String

    fun getAmount(): Double
}