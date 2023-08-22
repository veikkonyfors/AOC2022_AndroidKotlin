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
        assertEquals("[[], [6, 10, [8]], [7, [[0, 3, 8], [7, 0], 9, [8, 3], [2, 7]], 5, 6], [2, [[7], [7, 1, 8, 6]], 0]]",
            Signal("[[],[6,10,[8]],[7,[[0,3,8],[7,0],9,[8,3],[2,7]],5,6],[2,[[7],[7,1,8,6]],0]]").listSignal.toString())
        assertEquals("[4, fi.viware.AOC2022.day13distresssignal.puzz2.Signal, Mismatching bracketing :[4],[3]]", Signal("[4],[3]").listSignal.toString())
        assertEquals("[fi.viware.AOC2022.day13distresssignal.puzz2.Signal, Missing surrounding [ and/or ] :3,[4],[3]]", Signal("3,[4],[3]").listSignal.toString())
        assertEquals("[[4], [3]]", Signal("[[4],[3]]").listSignal.toString())
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
    fun testIsLower() {
        assertEquals(true, Signal("[[1], [2, 3, 4]]").isLower(Signal("[1, [2, [3, [4, [5, 6, 0]]]], 8, 9]")))
        assertEquals(false, Signal("[8]").isLower(Signal("[8]")))
        assertEquals(false, Signal("[[],[6,10,[8]],[7,[[0,3,8],[7,0],9,[8,3],[2,7]],5,6],[2,[[7],[7,1,8,6]],0]]")
            .isLower(Signal("[[],[6,10,[8]],[7,[[0,3,8],[7,0],9,[8,3],[2,7]],5,6],[2,[[7],[7,1,8,6]],0]]")))
        assertEquals(false, Signal("[1, [2, [3, [4, [5, 6, 0]]]], 8, 9]").isLower(Signal("[[1], [2, 3, 4]]")))
        assertEquals(false, Signal("[1,1,3,1,1]").isLower(Signal("[]")))
        assertEquals(true, Signal("[]").isLower(Signal("[1,1,3,1,1]")))
        assertEquals(false, Signal("[6]").isLower(Signal("[[5]]")))
        assertEquals(true,Signal("[1,[2]]").isLower(Signal("[[1],[3]]")))
        assertEquals(false,Signal("[1,[4]]").isLower(Signal("[[1],[3]]")))
        assertEquals(false,Signal("[[1],[4]]").isLower(Signal("[1,[3]]")))
        assertEquals(true,Signal("[[1],[2]]").isLower(Signal("[1,[3]]")))
        assertEquals(true,Signal("[[4,3],4,4]").isLower(Signal("[[4,4],4]")))
        assertEquals(false,Signal("[[4,3],5,4]").isLower(Signal("[[4,3],4]")))
        assertEquals(true, Signal("[5]").isLower(Signal("[[6]]")))
        assertEquals(true, Signal("[[5]]").isLower(Signal("[6]")))
        assertEquals(false, Signal("[[6]]").isLower(Signal("[5]")))
        assertEquals(false,Signal("[[4]]").isLower(Signal("[3]")))
        assertEquals(true, Signal("[[5]]").isLower(Signal("[6]")))
        assertEquals(false,Signal("[[4,4],4,4]").isLower(Signal("[[4,3],4]")))
        assertEquals(false,Signal("[[4,4],4,4]").isLower(Signal("[[4,4],4]")))
        assertEquals(true,Signal("[1,1,3,1,1]").isLower(Signal("[1,1,5,1,1]")))
        assertEquals(false,Signal("[1,1,5,1,1]").isLower(Signal("[1,1,3,1,1]")))
        assertEquals(false,Signal("[1,1,5,1,1]").isLower(Signal("[1,1,3,1]")))
        assertEquals(false,Signal("[1,1,3,1,1]").isLower(Signal("[1,1,3,1]")))
        assertEquals(true,Signal("[1,1,3]").isLower(Signal("[1,1,3,1]")))
        /*assertEquals(true,Signal("[]").isLower(Signal("[[]]")))
        assertEquals(false,Signal("[[]]").isLower(Signal("[]")))
        assertEquals(false,Signal("[[]]").isLower(Signal("[[]]")))
        assertEquals(false,Signal("[1,2,3,4]").isLower(Signal("[0,1,2,3]")))
        assertEquals(false,Signal("[[]]").isLower(Signal("[8,7,6]")))
        val signal1 = Signal("[[]]")
        println(signal1.isLower(Signal("[]")))*/

    }

    @Test
    fun testIsEqual() {
        assertEquals(false, Signal("[2, 3, 4]").isEqual(Signal("[2, [3, [4, [5, 6, 0]]]]")))
    }
}