package main.kotlin_code.check

class Beer(beer : Payable) : CheckDecorator(beer) {

    init {
        println("A pint of beer is added to the check.")
    }

    override fun getCost(): Double {
        return tempPayable.getCost() + 2.00
    }

    override fun getDescription(): String {
        return tempPayable.getDescription() + "- €2,00 A plastic cup of cheap beer.\n"
    }

    override fun getTitle(): String {
        return tempPayable.getTitle()
    }
}