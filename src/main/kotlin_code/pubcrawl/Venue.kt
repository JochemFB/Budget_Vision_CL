package kotlin_code.pubcrawl

import java.time.LocalDateTime

interface Venue {

    var startTime: LocalDateTime
    var reservationPeriod: String
    val name: String

    fun makereservations(StartTime: LocalDateTime)
}
