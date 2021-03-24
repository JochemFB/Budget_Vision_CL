package kotlin_code.pubcrawl

import java.time.LocalDateTime

class Bar(override var reservationPeriod: String, override val name: String): Venue {

    lateinit var startTime: LocalDateTime

    override fun makereservations(StartTime: LocalDateTime) {
        startTime = StartTime
    }
}

