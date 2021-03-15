package kotlin_code.check

class Check(title : String) : Payable {


    var checkTitle : String = title

    override fun getCost(): Double {
        return 0.00
    }

    override fun getDescription(): String {
        return "This is the check:\n"
    }

    override fun getTitle(): String {
        return checkTitle
    }

}