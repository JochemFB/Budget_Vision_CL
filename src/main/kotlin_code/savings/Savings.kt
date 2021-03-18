package kotlin_code.savings

class Savings() : Subject {

    var observers = ArrayList<Observer>()
    private var moneyBoxes = ArrayList<MoneyBox>()

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

    fun takeMoneyFromMoneyBox(number: Int, amount: Double) {
        try {
            this.moneyBoxes[(number - 1)].takeMoney(amount)
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