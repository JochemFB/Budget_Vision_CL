package kotlin.check

class Check(title : String) : Payable{


    var checkTitle : String = title

    var totalAmount : Double = 0.0

    var itemList = ArrayList<Payable>()



    fun printOverview(){

    }

    override fun getPrice() {
        TODO("Not yet implemented")
    }

    override fun getDescription() {
        TODO("Not yet implemented")
    }

}