package fi.viware.AOC2022.day13distresssignal.puzz2

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class SorterTest {

    lateinit var signalStrings: List<String>
    lateinit var sorter: Sorter


    @Before
    fun setUp() {

        val file = File(
            ".",
            "input_test"
        )
        val signalStringsFromFile = file.readLines()
        val dividerPackets = listOf("[[2]]", "[[6]]")
        signalStrings = signalStringsFromFile + dividerPackets
        sorter = Sorter(signalStrings)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun readFile(){
        println(sorter)
        assertEquals("[[1, 1, 3, 1, 1], [1, 1, 5, 1, 1], [], [[1], [], 2, 3, 4], [[1], 4], [], [9], [[8, 7, 6]], [], [[4, 5], 6, 7], [[4, 5], 6, 7, 8], [], [7, 7, 7, 7], [7, 7, 7], [], [], [3], [], [[[]]], [[]], [], [1, [2, [3, [4, [5, 6, 7]]]], 8, 9], [1, [2, [3, [4, [5, 6, 0]]]], 8, 9], [[2]], [[6]]]",
            sorter.toString())
    }
}