package main.kotlin_code.savings

import main.kotlin_code.transaction.Transaction


interface Savable {

    var transactionList : ArrayList<Transaction>

    fun addMoney(money: Double)

    fun takeMoney(money: Double)

    fun getCategory(): String

    fun getAmount(): Double
}