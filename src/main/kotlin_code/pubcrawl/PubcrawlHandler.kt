package main.kotlin_code.pubcrawl

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class PubcrawlHandler {

    private var pubcrawlList = ArrayList<PubCrawl>()
    private var validDateTime = false

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm")

    private val scan = Scanner(System.`in`)

    fun showAllOptions() {
        while (true) {
            printMainMenu()
            val option = scan.nextLine().trim().toInt()

            //Option 1: Show pubcrawls
            if (option == 1) {
                showAllPubcrawls()
            }

            //Option 2: Create new pubcrawl
            else if (option == 2) {
                createPubcrawl()
            }

            //option 3 edit a pubcrawl
            else if (option == 3) {
                editPubcrawl()
            }

            //0ption 4 remove an existing pubcrawl
            else if (option == 4) {
                removePubcrawl()
            }
            //0ption 5 Show all venues of a pubcrawl
            else if (option == 5) {

                showAllPubcrawls()

                if (pubcrawlList.size == 0) {
                    continue
                }

                println("Enter pubcrawl number to see venues")
                val pubcrawlNumber = scan.nextLine().trim().toInt()

                if (pubcrawlNumber <= pubcrawlList.size && pubcrawlNumber > 0) {
                    showPubcrawlVenues(pubcrawlList[pubcrawlNumber - 1])
                } else {
                    println("This pubcrawl does not exist.")
                }
            }
            //Option 6: Go back to the main menu
            else if (option == 6) {
                break
            } else {
                println("This is not an option.")
                break
            }
        }
    }

    /**
     * functie voor het printen van de main menu
     */
    private fun printMainMenu() {
        println("Pubcrawl:")
        println("--------------")
        println("1. Show all pubcrawls")
        println("2. Make new pubcrawl")
        println("3. edit venues of existing pubcrawl and make reservations")
        println("4. remove an existing pubcrawl")
        println("5. show all venues from a pubcrawl")
        println("6. go back to main menu")
        println("--------------")
        println("Enter your choice:")
    }

    /**
     * functie die alle bestaande pubcrawls print. Hierbij word een nummer + de pubcrawl naam geprint
     */
    private fun showAllPubcrawls() {
        println("--------------")
        println("pubcrawls:")
        println("--------------")
        if (pubcrawlList.isEmpty()) {
            println("There are no pubcrawls")
        } else {
            var i = 1
            for (pubcrawl in pubcrawlList) {

                if (pubcrawl.reservationPeriod.toInt() > 0) {
                    println(i.toString() + ". " + pubcrawl.name + " (has a reservationperiod of: " + pubcrawl.reservationPeriod + ")")
                } else {
                    println(i.toString() + ". " + pubcrawl.name)
                }
                i++
            }
        }
        println("--------------")
    }

    /**
     * laat alle venues zien van een pubcrawl
     */
    private fun showPubcrawlVenues(pubCrawl: PubCrawl) {
        val venues = pubCrawl.getvenues()
        println("--------------")
        println("Venues:")
        println("--------------")
        if (venues.isEmpty()) {
            println("there are no venues")
        } else {
            var i = 1
            for (venue in venues) {
                println(i.toString() + ". " + venue.name + " (with a reservationperiod of: " + venue.reservationPeriod + ")")
                if (pubCrawl.reservationPeriod.toInt() > 0) {
                    pubCrawl.makeReservations(pubCrawl.startTime)
                    println("StartTime: " + venue.startTime.format(dateTimeFormatter))
                }
                i++
            }
        }
        println("--------------")
    }

    /**
     * toevoegen van een venue aan een pubcrawl
     */
    private fun addVenue(pubCrawl: PubCrawl) {
        println("choose a venue")
        println("1. Restaurant")
        println("2. Cafe")
        println("3. Bar")
        println("4. Cancel")

        val venuetype = scan.nextLine().trim()

        if (venuetype.toInt() == 4 || venuetype.toInt() == 0) {
            return
        }
        println("enter how long you want to stay at the venue in minutes")
        val time = scan.nextLine().trim().toInt()
        println("enter the name of the venue")
        val venueName = scan.nextLine().toString()

        when (venuetype.toInt()) {
            1 -> {
                pubCrawl.addVenue(Restaurant(time.toString(), venueName))
            }
            2 -> {
                pubCrawl.addVenue(Cafe(time.toString(), venueName))
            }
            3 -> {
                pubCrawl.addVenue(Bar(time.toString(), venueName))
            }
        }
        return
    }

    /**
     * verwijderen van een venue van een pubcrawl
     */
    private fun removeVenue(pubCrawl: PubCrawl) {
        showPubcrawlVenues(pubCrawl)

        if (pubcrawlList.size > 0) {
            println("enter the number of the venue you want to remove")
            val venueNumber = scan.nextLine().trim().toInt()

            pubCrawl.removeVenue(pubCrawl.venues[venueNumber - 1])

            //refresh the reservations
            pubCrawl.makeReservations(pubCrawl.startTime)
        } else {
            println("there are no venues yet")
        }
    }

    /**
     * maken van de reservations van alle venues die op dat moment aan de pubcrawl zijn toegevoegd
     */
    private fun makeReservation(pubCrawl: PubCrawl) {

        println("enter StartTime of pubcrawl")
        println("Date and time (d-M-yyyy HH:mm) (enter 0 to cancel):")
        var dateInput = scan.nextLine().trim()

        if (dateInput == "0") {
            // Break out of loop to return to main menu
            return
        }
        while (!validDateTime) {
            // Check if dateInput is in the correct format (dd-MM-yyyy HH:mm)
            validDateTime = try {
                val dateTime = LocalDateTime.parse(dateInput, dateTimeFormatter)
                pubCrawl.makeReservations(dateTime)
                true
            } catch (e: DateTimeParseException) {
                println("The date is incorrect. Please try again (enter 0 to cancel).")
                // Get user input
                dateInput = scan.nextLine().trim()
                if (dateInput == "0") {
                    // Break out of loop to return to main menu
                    break
                }
                false
            }
        }
        if (dateInput == "0") {
            // Break out of loop to return to main menu
            return
        }
        return
    }

    /**
     * menu om een specifieke pubcrawl te editen
     * word doorgestuurd naar een andere method voor het maken van reservations, toevoegen venue of om een venue te verwijderen
     */
    private fun editPubcrawl() {
        showAllPubcrawls()

        var pubcrawlNumber = -1

        if (pubcrawlList.size < 1) {
            return
        }

        while (pubcrawlNumber <= pubcrawlList.size) {

            println("Enter pubcrawl number to edit:")
            println("Enter 0 to exit")
            pubcrawlNumber = scan.nextLine().trim().toInt()

            if (pubcrawlList.size < pubcrawlNumber) {
                println("There is no pubcrawl with number $pubcrawlNumber")
                break
            } else if (pubcrawlNumber == 0) {
                break
            }
            //gets the selected pubcrawl from the pubcrawlList
            val pubcrawl = pubcrawlList[pubcrawlNumber - 1]

            showPubcrawlVenues(pubcrawl)

            println("choose what you want to do")
            println("1. add a venue")
            println("2. remove a venue")
            println("3. make reservations")

            when (scan.nextLine().trim().toInt()) {
                1 -> {
                    addVenue(pubcrawl)
                }
                2 -> {
                    removeVenue(pubcrawl)
                }
                3 -> {
                    makeReservation(pubcrawl)
                }
                0 -> {
                    break
                }
            }
            break
        }
    }

    /**
     * aanmaken van een nieuwe pubcrawl
     */
    private fun createPubcrawl() {
        println("Enter pub crawl name:")
        val pubcrawlname = scan.nextLine().toString()
        pubcrawlList.add(PubCrawl(pubcrawlname))
    }

    /**
     * verwijderen van een bestaande pubcrawl
     */
    private fun removePubcrawl() {
        showAllPubcrawls()

        println("Enter pubcrawl number to remove:")
        val pubcrawlNumber = scan.nextLine().trim().toInt()

        //gets the selected pubcrawl from the pubcrawlList
        pubcrawlList.remove(pubcrawlList[pubcrawlNumber - 1])
    }
}
