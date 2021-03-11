package kotlin_code.check

class Check(title : String) : Payable {


    var checkTitle : String = title

    var totalAmount : Double = 0.0

    var itemList = ArrayList<Payable>()



    fun printOverview(){

    }



    override fun getCost(): Double {
        return 0.00
    }

    override fun getDescription(): String {
        return "This is the check:"
    }

}