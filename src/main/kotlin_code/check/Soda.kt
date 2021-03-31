package main.kotlin_code.check

class Soda(soda : Payable) : CheckDecorator(soda) {

    init {
        println("A can of soda is added to the check.")
    }

    override fun getCost(): Double {
        return tempPayable.getCost() + 1.50
    }

    override fun getDescription(): String {
        return tempPayable.getDescription() + "- €1,50 A can of sparkling soda.\n"
    }

    override fun getTitle(): String {
        return tempPayable.getTitle()
    }
}