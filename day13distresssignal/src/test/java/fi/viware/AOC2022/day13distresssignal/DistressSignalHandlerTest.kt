package fi.viware.AOC2022.day13distresssignal

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class DistressSignalHandlerTest {

    lateinit var signalLines: List<String>
    lateinit var distressSignalHandler: DistressSignalHandler


    @Before
    fun setUp() {
        distressSignalHandler= DistressSignalHandler()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testCompareSignals(){
        //val rightOrder:List<Boolean> =listOf(true,true,false,true,false,true,false,false)
        distressSignalHandler.compare()

        println("Sum: ${distressSignalHandler.countSumValidSignalIndexes()}")
    }
}