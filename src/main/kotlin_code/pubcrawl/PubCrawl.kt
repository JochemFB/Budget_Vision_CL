package main.kotlin_code.pubcrawl

import java.time.LocalDateTime

class PubCrawl(override val name: String) : Venue {

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

    override fun makeReservations(startTime: LocalDateTime) {
        this.startTime = startTime
        var venueStartTime = startTime
        var totalTime = 0
        var minutes: Long
        for (venue in venues){
            venue.makeReservations(venueStartTime)
            minutes = venue.reservationPeriod.toLong()
            venueStartTime = venueStartTime.plusMinutes(minutes)
            totalTime += minutes.toInt()
        }
        reservationPeriod = totalTime.toString()
    }
}
