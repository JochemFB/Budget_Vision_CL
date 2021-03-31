package main.kotlin_code.check

class Wine(wine : Payable) : CheckDecorator(wine) {

    init {
        println("A glass of wine is added to the check.")
    }

    override fun getCost(): Double {
        return tempPayable.getCost() + 4.00
    }

    override fun getDescription(): String {
        return tempPayable.getDescription() + "- €4,00 Glass of expensive wine.\n"
    }

    override fun getTitle(): String {
        return tempPayable.getTitle()
    }
}