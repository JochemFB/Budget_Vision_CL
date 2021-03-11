package kotlin_code.check

abstract class CheckDecorater(val tempPayable: Payable) : Payable {

    override fun getCost(): Double {
        return tempPayable.getCost()
    }

    override fun getDescription(): String {
        return tempPayable.getDescription()
    }
}