package kotlin_code.savings

class SavingDisplay(var saving: Savings) : Observer, Display {

    private var moneyBoxes : ArrayList<MoneyBox>;

    init {
        saving.registerObserver(this)
        moneyBoxes = saving.getMoneyBoxes()
    }

    fun hasNoMoneyBoxes() : Boolean{
        return this.moneyBoxes.isEmpty()
    }

    /**
     * Update de moneyboxes naar de huidge staat.
     */
    override fun update(amount: ArrayList<Observer>) {
        this.moneyBoxes = saving.getMoneyBoxes()
    }

    /**
     * Toon alle moneyboxes en het bedrag dat erin zit
     */
    override fun display() {

            if (saving.getMoneyBoxes().isEmpty()) {
                println("There are no saving categories yet.")
            } else {
                var counter = 1
                saving.getMoneyBoxes().forEach() {
                    println("$counter. ${it.getCategory()}: €${it.getAmount()}")
                    counter++
                }
            }
    }
}