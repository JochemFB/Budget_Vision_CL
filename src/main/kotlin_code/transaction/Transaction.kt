package main.kotlin_code.transaction

interface Transaction {
    fun getDateTime(): String
    fun getAmount(): String
    fun getDescription(): String
    override fun toString(): String
}