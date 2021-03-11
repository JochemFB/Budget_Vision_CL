package kotlin_code.check

class Soda(beer : Payable) : CheckDecorater(beer) {

    init {
        println("A can of soda is added to the check.")
    }

    override fun getCost(): Double {
        return tempPayable.getCost() + 1.50
    }

    override fun getDescription(): String {
        return tempPayable.getDescription() + "A can of sparkling soda.\n"
    }
}