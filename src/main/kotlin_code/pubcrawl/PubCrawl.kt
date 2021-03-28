package kotlin_code.pubcrawl

import java.time.LocalDateTime

class PubCrawl(override val name: String) :Venue {

    override var startTime: LocalDateTime = LocalDateTime.now()
    override var reservationPeriod = "0"

    var venues = ArrayList<Venue>()

    fun addVenue(Venue: Venue){
        venues.add(Venue)
    }

    fun removeVenue(Venue: Venue){
        venues.remove(Venue)
    }

    fun getvenues(): ArrayList<Venue>{
        return venues
    }

    override fun makereservations(StartTime: LocalDateTime) {
        startTime = StartTime
        var totalTime = 0
        var minutes: Int
        for (venue in venues){
            venue.makereservations(StartTime)
            // mm
            minutes = venue.reservationPeriod.toInt()
            StartTime.plusMinutes(minutes.toLong())
            totalTime += minutes
        }
        reservationPeriod = totalTime.toString()
    }
}
