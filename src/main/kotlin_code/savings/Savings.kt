package kotlin_code.savings

class Savings() : Subject {

    var observers = ArrayList<Observer>()
    private var moneyBoxes = ArrayList<MoneyBox>()

    //Savings is een Singleton
    //Companion objects zijn een natuurlijke Singleton in Kotlin
    companion object{
        val instance = Savings()
    }


    fun createMoneyBox(category: String, amount: Double) {
        moneyBoxes.add(MoneyBox(category, amount))
        notifyObserver()
    }

    fun addMoneyToMoneyBox(number: Int, amount: Double) {
        try {
            this.moneyBoxes[(number - 1)].addMoney(amount)
            notifyObserver()
        } catch (e: IndexOutOfBoundsException) {
            println("This money box does not exist.")
        }
    }

    /**
     * Haal geld uit de spaarpot als deze bestaat.
     */
    fun takeMoneyFromMoneyBox(number: Int, amount: Double) {
        try {
            this.moneyBoxes[(number - 1)].takeMoney(amount)
            println("Money was taken from the box.")
            notifyObserver()
        } catch (e: IndexOutOfBoundsException) {
            println("This money box does not exist.")
        }
    }

    fun getMoneyBoxes(): ArrayList<MoneyBox> {
        return this.moneyBoxes
    }

    override fun registerObserver(newObserver: Observer) {
        observers.add(newObserver);

    }

    override fun unregisterObserver(observer: Observer) {
        val observerIndex = observers.indexOf(observer)
        observers.removeAt(observerIndex)
    }

    override fun notifyObserver() {
        observers.forEach() {
            it.update(observers)
        }
    }
}