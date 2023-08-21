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

    /**
     * Received "Comparison method violates its general contract!" for actual input.Worked for test input.
     * Pinpointed it to be that it's a question of the size of the list. With about a hundredlines from the input
     * comparator fails, but when one takes two line away, it works. Doesn't matter which lines.
     * Somehow comparator fails with big enough input.
     * Change -Xms2048 -Xmx4096 in run/edit configurations, didn't help.
     */
    @Test
    fun testComparatorConsistency() {
        val file = File(
            ".",
            "input_test"
        )
        val signalStringsFromFile = file.readLines()
        val nonEmptySignalStringsFromFile = signalStringsFromFile.filter { it.isNotEmpty() }
        val dividerPackets = listOf("[2]", "[6]")
        var signalStrings: List<String> = nonEmptySignalStringsFromFile + dividerPackets

        var listOfSignals:MutableList<Signal> = mutableListOf()
        signalStrings.forEach {
            listOfSignals.add(Signal(it))
        }
        // Check consistency
        listOfSignals.forEach { it1->
            listOfSignals.forEach { it2->
                if (it1 == it2) return@forEach
                println("$it1, $it2\n${it1.isLower(it2)}, ${it2.isLower(it1)}")
                assertEquals(true, it1.isLower(it2) != it2.isLower(it1))
            }
        }
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
        val n = sorter.sortedListOfSignals.indexOfFirst {
            it.listSignal.toString() == "[2]"
        }
        val m = sorter.sortedListOfSignals.indexOfFirst {
            it.listSignal.toString() == "[6]"
        }
        println("$n, $m, ${(n + 1) * (m + 1)}")
    }

    /*
    120, 212, 25773
    That's not the right answer; your answer is too high. If you're stuck, make sure you're using the full input data;
    there are also some general tips on the about page, or you can ask for hints on the subreddit.
    Please wait one minute before trying again. (You guessed 25773.) [Return to Day 13]
     */
    @Test
    fun solvePuzz2(){
        val file = File(
            ".",
            "input"
        )
        val signalStringsFromFile = file.readLines()
        val nonEmptySignalStringsFromFile = signalStringsFromFile.filter { it.isNotEmpty() }
        val dividerPackets = listOf("[2]", "[6]")
        var signalStrings: List<String> = nonEmptySignalStringsFromFile + dividerPackets
        val sorter = Sorter(signalStrings)
        println(sorter)

        var i = 0
        sorter.sortedListOfSignals.forEach {
            println("${i++} $it")

        }

        val n = sorter.sortedListOfSignals.indexOfFirst {
            it.listSignal.toString() == "[2]"
        }
        val m = sorter.sortedListOfSignals.indexOfFirst {
            it.listSignal.toString() == "[6]"
        }
        println("$n, $m, ${(n + 1) * (m + 1)}")
    }
}