package kotlin_code.savings

interface Subject {

    fun registerObserver(observer: Observer)

    fun unregisterObserver(observer: Observer)

    fun notifyObserver()
}