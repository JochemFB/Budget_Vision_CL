package kotlin_code.pubcrawl

import kotlin_code.check.Check
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import java.time.format.DateTimeFormatter




class PubcrawlHandler {

    var pubcrawlList = ArrayList<PubCrawl>()

    val scan = Scanner(System.`in`)

    fun showAllOptions() {

        printMainMenu()

        while (true) {
            val option = scan.nextLine().trim().toInt()

            //Option 1: Show pubcrawls
            if (option == 1) {

                showAllPubcrawls()

                printMainMenu()

            }
            //Option 2: Create new pubcrawl
            else if (option == 2) {
                println("Enter pub crawl name:")
                val pubcrawlname = scan.nextLine().toString()
                pubcrawlList.add(PubCrawl(pubcrawlname, LocalDateTime.now()))

                printMainMenu()

            }
            //option 3 edit a pubcrawl
            else if (option == 3) {

                showAllPubcrawls()

                println("Enter pubcrawl name to edit:")
                val pubcrawlname = scan.nextLine().toString()

                //gets the selected pubcrawl from the pubcrawlList
                val pubcrawl = getPubcrawl(pubcrawlname, pubcrawlList)

                showPubcrawlVenues(pubcrawl)

                println("choose what you want to do")
                println("1. add a venue")
                println("2. remove a venue")
                println("3. make reservations")

                val editOption = scan.nextLine().trim().toInt()

                if(editOption == 1) {
                    addvenue(pubcrawl)
                }else if(editOption == 2){
                    removeVenue(pubcrawl)
                }else if(editOption == 3){
                    makeReservation(pubcrawl)
                }

                printMainMenu()

            }
            //Option 5: Go back to the main menu
            else if (option == 4) {
                break
            } else {
                println("This is not an option.")
            }
        }
    }

    fun printMainMenu(){
        println("Pubcrawl:");
        println("--------------");
        println("1. Show all pubcrawls");
        println("2. Make new pubcrawl");
        println("3. edit venues of existing pubcrawl and make reservations")
        println("4. go back to main menu")
        println("--------------");
        println("Enter your choice:");
    }

    fun showAllPubcrawls(){
        println("--------------")
        println("pubcrawls:")
        println("--------------")
        for(pubcrawl in pubcrawlList)
        {
            println(pubcrawl.name)
        }
        if(pubcrawlList.isEmpty()){
            println("There are no pubcrawls")
        }
        println("--------------")
    }

    fun showPubcrawlVenues(pubCrawl: PubCrawl){
        val venues = pubCrawl.getvenues()
        println("--------------")
        println("Venues:")
        println("--------------")
        if(venues.isEmpty()){
            println("there are no venues")
        }else{
            for (venue in venues) {
                println(venue.name)
            }
        }
        println("--------------")
    }

    fun addvenue(pubCrawl: PubCrawl){
        println("choose a venue")
        println("1. Restaurant")
        println("2. Cafe")
        println("3. Bar")
        val venuetype = scan.nextLine().trim().toInt()
        if (venuetype > 3 || venuetype < 1) {
            println("wrong type")
            //TODO: hier moet je eigenlijk uit de method worden gegooit.
        }
        println("enter how long you want to stay at the venue in minutes")
        val time = scan.nextLine().toString()
        println("enter the name of the venue")
        val venueName = scan.nextLine().toString()
        if (venuetype == 1) {
            pubCrawl.addVenue(Restaurant(time, venueName))
        } else if (venuetype == 2) {
            pubCrawl.addVenue(Cafe(time, venueName))
        } else if (venuetype == 3) {
            pubCrawl.addVenue(Bar(time, venueName))
        }
    }

    fun getPubcrawl(pubCrawlName: String, pubcrawlList: ArrayList<PubCrawl>): PubCrawl{
        for (i in pubcrawlList) {
            if (i.name == pubCrawlName) {
                return i
            }
        }
        throw Exception("pubcrawl doesnt exist")
    }

    fun getVenue(venueName: String, pubCrawl: PubCrawl):Venue{
        for (venue in pubCrawl.venues){
            if(venue.name == venueName){
                return venue
            }
        }
        throw Exception("venue doesnt exist")
    }

    fun removeVenue(pubCrawl: PubCrawl){
        showPubcrawlVenues(pubCrawl)

        println("enter the name of the venue you want to remove")
        val venueName = scan.nextLine()

        pubCrawl.removeVenue(getVenue(venueName, pubCrawl))
    }

    fun makeReservation(pubCrawl: PubCrawl){
        //TODO: werkt nog niet
        println("enter StartTime of pubcrawl")
        println("format")
        val Time = scan.nextLine().toString()
        val localDateTime = LocalDateTime.parse(Time)
        pubCrawl.makereservations(localDateTime)
    }
}
