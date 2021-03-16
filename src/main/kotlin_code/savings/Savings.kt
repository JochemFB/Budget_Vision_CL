package kotlin_code.savings

class Savings(private val savingsCategory: String, private var amount: Double) : Subject{


    fun addMoney(money: Double) {
        this.amount += money
    }

    fun takeMoney(money: Double){
        this.amount -= money
    }

    fun printCategory(){
        println("Category: $savingsCategory")
    }

    fun getAmount() : Double{
        return this.amount
    }

    override fun registerObserver() {
        TODO("Not yet implemented")
    }

    override fun removeObserver() {
        TODO("Not yet implemented")
    }

    override fun notifyObserver() {
        TODO("Not yet implemented")
    }


}