package kotlin_code.pubcrawl

import java.time.LocalDateTime

interface Venue {

    var reservationPeriod: String
    val name: String

    fun makereservations(StartTime: LocalDateTime)
}
