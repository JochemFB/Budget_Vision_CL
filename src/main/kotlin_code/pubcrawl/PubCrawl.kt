package kotlin_code.pubcrawl

class PubCrawl:venue {

    var venues = ArrayList<venue>()

    fun addVenue(venue: venue){
        venues.add(venue)
    }

    fun removeVenue(venue: venue){
        venues.remove(venue)
    }

    fun getvenues(): ArrayList<venue>{
        return venues
    }

    override fun makereservations(StartTime: Int) {
        TODO("Not yet implemented")
    }
}
