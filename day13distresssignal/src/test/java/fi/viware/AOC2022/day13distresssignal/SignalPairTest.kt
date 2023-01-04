package fi.viware.AOC2022.day13distresssignal

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class SignalPairTest {

    lateinit var signalPair:SignalPair


    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun testSignalPair(){

        signalPair=SignalPair(Signal(listOf(listOf(4,5),6,7)),Signal(listOf(listOf(4,5),6,7,8)),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertTrue(signalPair.hasRightOrder)

        signalPair=SignalPair(Signal(listOf(listOf(4,4),4,4)),Signal(listOf(listOf(4,4),4,4,4)),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertTrue(signalPair.hasRightOrder)

        signalPair=SignalPair(Signal(listOf(1,1,3,1,1)),Signal(listOf(1,1,5,1,1)),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertTrue(signalPair.hasRightOrder)

        signalPair=SignalPair(Signal(listOf(listOf(1),listOf(2,3,4))),Signal(listOf(listOf(1),4)),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertTrue(signalPair.hasRightOrder)

        signalPair=SignalPair(Signal(listOf(9)),Signal(listOf(listOf(8,7,6))),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertFalse(signalPair.hasRightOrder)

        signalPair=SignalPair(Signal(listOf(6)),Signal(listOf(listOf(8,7,6))),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertTrue(signalPair.hasRightOrder)

        signalPair=SignalPair(Signal(listOf(7,7,7,7)),Signal(listOf(7,7,7)),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertFalse(signalPair.hasRightOrder)


        signalPair=SignalPair(Signal(listOf<Any>()),Signal(listOf(3)),0)
        signalPair.compare()
        println("$signalPair\n\n")
        assertTrue(signalPair.hasRightOrder)
    }
}