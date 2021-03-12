package kotlin_code.check

class Beer(beer : Payable) : CheckDecorator(beer) {

    init {
        println("A pint of beer is added to the check.")
    }

    override fun getCost(): Double {
        return tempPayable.getCost() + 2.00
    }

    override fun getDescription(): String {
        return tempPayable.getDescription() + "A plastic cup of cheap beer.\n"
    }
}