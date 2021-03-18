package kotlin_code.pubcrawl

import java.time.LocalDateTime

interface Venue {

    var reservationPeriod: String

    fun makereservations(StartTime: LocalDateTime)
}
