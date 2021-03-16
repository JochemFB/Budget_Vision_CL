package kotlin_code.savings

interface Subject {

    fun registerObserver()

    fun removeObserver()

    fun notifyObserver()
}