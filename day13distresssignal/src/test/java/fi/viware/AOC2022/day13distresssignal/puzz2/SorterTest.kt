package fi.viware.AOC2022.day13distresssignal.puzz2

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class SorterTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun readLines() {

        val file = File(
            ".",
            "input_test"
        )
        val signalStringsFromFile = file.readLines()
        println(signalStringsFromFile)
        assertEquals(
            "[[1,1,3,1,1], [1,1,5,1,1], , [[1],[2,3,4]], [[1],4], , [9], [[8,7,6]], , [[4,4],4,4], [[4,4],4,4,4], , [7,7,7,7], [7,7,7], , [], [3], , [[[]]], [[]], , [1,[2,[3,[4,[5,6,7]]]],8,9], [1,[2,[3,[4,[5,6,0]]]],8,9]]",
            signalStringsFromFile.toString()
        )
    }

    @Test
    fun testSorter() {
        val sig = Signal("[[1,1,3,1,1], [1,1,5,1,1], [[1],[2,3,4]], [[1],4], [9], [[8,7,6]], " +
                "[[4,4],4,4], [7,7,7,7], [7,7,7], [], [3], [[[]]], [[]], [1,[2,[3,[4,[5,6,7]]]],8,9], [1,[2,[3,[4,[5,6,0]]]],8,9]]",)
        println(sig.listSignal)
        val testSorter = Sorter(sig.getAsListStrings())
        println(testSorter.toString())
    }

    @Test
    fun solvePuzz2TestInput(){
        val file = File(
            ".",
            "input_test"
        )
        val signalStringsFromFile = file.readLines()
        val nonEmptySignalStringsFromFile = signalStringsFromFile.filter { it.isNotEmpty() }
        val dividerPackets = listOf("[2]", "[6]")
        var signalStrings: List<String> = nonEmptySignalStringsFromFile + dividerPackets
        val sorter = Sorter(signalStrings)
        println(sorter)
        assertEquals(
            "[[], [[]], [[[]]], [1, 1, 3, 1, 1], [1, 1, 5, 1, 1], [[1], [2, 3, 4]], " +
                    "[1, [2, [3, [4, [5, 6, 0]]]], 8, 9], [1, [2, [3, [4, [5, 6, 7]]]], 8, 9], " +
                    "[[1], 4], [2], [3], [[4, 4], 4, 4], [[4, 4], 4, 4, 4], [6], [7, 7, 7], " +
                    "[7, 7, 7, 7], [[8, 7, 6]], [9]]",
            sorter.toStringSorted()
        )
    }
}