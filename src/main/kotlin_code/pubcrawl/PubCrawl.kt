package kotlin_code.pubcrawl

import java.lang.StrictMath.floor
import java.time.LocalDateTime

class PubCrawl(override val name: String, val pubcrawlStartTime: LocalDateTime) :Venue {

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
        //TODO: werkent maken
        var totalTime = 0
        var minutes = 0
        for (venue in venues){
            venue.makereservations(StartTime)
            // HH:mm
            val time = venue.reservationPeriod.split(":")
            minutes = time[0].toInt() * 60 + time[1].toInt()
            StartTime.plusMinutes(minutes.toLong())
            totalTime += minutes
        }
        val Hours = floor((minutes / 60).toDouble())
        reservationPeriod = Hours.toString() + ":" + (minutes - Hours).toString()
    }
}
