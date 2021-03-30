package kotlin_code.savings

import kotlin_code.transaction.Transaction

class MoneyBox(private val savingsCategory: String, private var amount: Double, override var transactionList: ArrayList<Transaction> = ArrayList()) : Savable {

    override fun addMoney(money: Double) {
        this.amount += money
    }

    override fun takeMoney(money: Double) {
        this.amount -= money
    }

    override fun getCategory(): String {
        return this.savingsCategory
    }

    /**
     * Return the amount of money in a money box
     */
    override fun getAmount(): Double {
        return this.amount
    }
}