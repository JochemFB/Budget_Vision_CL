package kotlin_code.pubcrawl

import java.time.LocalDateTime

class Cafe(override var reservationPeriod: String, override val name: String): Venue {

    override var startTime: LocalDateTime = LocalDateTime.now()

    override fun makereservations(StartTime: LocalDateTime) {
        startTime = StartTime
    }
}
