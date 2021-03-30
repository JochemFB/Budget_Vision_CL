package kotlin_code.check

abstract class CheckDecorator(val tempPayable: Payable) : Payable {

    override fun getCost(): Double {
        return tempPayable.getCost()
    }

    override fun getDescription(): String {
        return tempPayable.getDescription()
    }

    override fun getTitle(): String {
        return ""
    }
}