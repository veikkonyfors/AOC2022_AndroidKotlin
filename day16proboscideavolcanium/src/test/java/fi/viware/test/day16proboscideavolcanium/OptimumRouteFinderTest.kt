package fi.viware.test.day16proboscideavolcanium

import fi.viware.test.day16proboscideavolcanium.optimumroutefinder.OptimumRouteFinder
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class OptimumRouteFinderTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun traverseRoute(){
        val inputFile = File(".","input")
        val inputLines = inputFile.readLines()
        val optimumRoute = OptimumRouteFinder(inputLines)
        println(optimumRoute.mapOfAllValvesByName)
        println(optimumRoute.mapOfConsiderableValvesByName)
        optimumRoute.printHops()

        val startingNode = "AA"
        val visitedNodes = mutableSetOf<String>()
        val status = OptimumRouteFinder.Status(0)
        //val maxFlow = optimumRoute.traverseRoute(startingNode, 0,0, 0, visitedNodes, 30, status)
        //println(maxFlow)
        //println(optimumRoute)
        //assertEquals("{AA={BB=2, CC=3, DD=2, EE=3, HH=6, JJ=3}, BB={CC=2, DD=3, EE=4, HH=7, JJ=4}, CC={BB=2, DD=2, EE=3, HH=6, JJ=5}, DD={BB=3, CC=2, EE=2, HH=5, JJ=4}, EE={BB=4, CC=3, DD=2, HH=4, JJ=5}, HH={BB=7, CC=6, DD=5, EE=4, JJ=8}, JJ={BB=4, CC=5, DD=4, EE=5, HH=8}}",optimumRoute.toString())
    }
}