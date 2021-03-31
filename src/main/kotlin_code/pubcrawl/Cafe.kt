package main.kotlin_code.pubcrawl

import java.time.LocalDateTime

class Cafe(override var reservationPeriod: String, override val name: String): Venue {

    override lateinit var startTime: LocalDateTime

    override fun makeReservations(startTime: LocalDateTime) {
        this.startTime = startTime
    }
}
