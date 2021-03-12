package kotlin_code.check

class Wine(wine : Payable) : CheckDecorator(wine) {

    init {
        println("A glass of wine is added to the check.")
    }

    override fun getCost(): Double {
        return tempPayable.getCost() + 4.00
    }

    override fun getDescription(): String {
        return tempPayable.getDescription() + "Glass of expensive wine.\n"
    }
}