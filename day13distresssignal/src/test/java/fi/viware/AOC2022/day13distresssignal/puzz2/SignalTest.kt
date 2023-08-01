package fi.viware.AOC2022.day13distresssignal.puzz2

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SignalTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getSignalString() {
        val signal1 = Signal("[1,1,3,1,1]")
        val signal2 = Signal("[1,1,5,1,1]")
        println("${signal1.stringSignal}, ${signal2.stringSignal}")
        assertEquals("[1,1,3,1,1] [1,1,5,1,1]", "${signal1.stringSignal} ${signal2.stringSignal}")
    }

    @Test
    fun testParseStringSignal2ListSignal() {
        assertEquals("[[4]]", Signal("[[4]]").listSignal.toString())
        assertEquals("[5]", Signal("[5]").listSignal.toString())
        assertEquals("[3, 4, 5, 5]", Signal("[3,4,5,5]").listSignal.toString())
        assertEquals("[3, 4, 5, 5]", Signal("[3,4,5,5]").listSignal.toString())
        assertEquals("[3, [4, 5], 6]", Signal("[3,[4,5],6]").listSignal.toString())
        assertEquals("[1, [2, [3, [4, [5, 6, 7]]]], 8, 9]",Signal("[1 , [ 2  ,[3,[4,[5,6,7]]]],8,9]").listSignal.toString())
        assertEquals("[]",Signal("[  ]").listSignal.toString())
        assertEquals("[[]]",Signal("[ [] ]").listSignal.toString())
        val signal1 = Signal("[[]]")
        println(signal1.listSignal)
    }

    @Test
    fun testExtractSubSignalString() {
        val testSignal = Signal("")  // Dummy signal required to test extractSubSignalString
        assertEquals("[3,[4,5],5]", testSignal.extractSubSignalString("[3,[4,5],5],6]"))
        assertEquals("[3,4,5]", testSignal.extractSubSignalString("[3,4,5],6]"))
        assertEquals("[ 2  ,[3,[4,[5,6,7]]]]", testSignal.extractSubSignalString("[ 2  ,[3,[4,[5,6,7]]]],8,9]"))
    }

    @Test
    fun testIsLowerOrEqual() {
        assertEquals(true, Signal("[5]").isLowerOrEqual(Signal("[[6]]")))
        assertEquals(false,Signal("[1,[4]]").isLowerOrEqual(Signal("1,[3]")))
        assertEquals(false,Signal("[[4]]").isLowerOrEqual(Signal("[3]")))
        assertEquals(true, Signal("[[5]]").isLowerOrEqual(Signal("[6]")))
        assertEquals(false,Signal("[[4,4],4,4]").isLowerOrEqual(Signal("[[4,3],4]")))
        assertEquals(true,Signal("[[4,4],4,4]").isLowerOrEqual(Signal("[[4,4],4]")))
        assertEquals(true,Signal("[[4,3],4,4]").isLowerOrEqual(Signal("[[4,4],4]")))
        assertEquals(true,Signal("[1,1,3,1,1]").isLowerOrEqual(Signal("[1,1,5,1,1]")))
        assertEquals(false,Signal("[1,1,5,1,1]").isLowerOrEqual(Signal("[1,1,3,1,1]")))
        assertEquals(false,Signal("[1,1,5,1,1]").isLowerOrEqual(Signal("[1,1,3,1]")))
        assertEquals(false,Signal("[1,1,3,1,1]").isLowerOrEqual(Signal("[1,1,3,1]")))
        assertEquals(true,Signal("[1,1,3]").isLowerOrEqual(Signal("[1,1,3,1]")))
        /*assertEquals(true,Signal("[]").isLower(Signal("[[]]")))
        assertEquals(false,Signal("[[]]").isLower(Signal("[]")))
        assertEquals(false,Signal("[[]]").isLower(Signal("[[]]")))
        assertEquals(false,Signal("[1,2,3,4]").isLower(Signal("[0,1,2,3]")))
        assertEquals(false,Signal("[[]]").isLower(Signal("[8,7,6]")))
        val signal1 = Signal("[[]]")
        println(signal1.isLower(Signal("[]")))*/

    }
}