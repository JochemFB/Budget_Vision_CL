package kotlin_code.savings

class SavingDisplay(var saving: Savings) : Observer, Display {

    private var moneyBoxes : ArrayList<MoneyBox>;

    init {
        saving.registerObserver(this)
        moneyBoxes = saving.getMoneyBoxes()
    }

    override fun update(amount: ArrayList<Observer>) {
        this.moneyBoxes = saving.getMoneyBoxes()
    }

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