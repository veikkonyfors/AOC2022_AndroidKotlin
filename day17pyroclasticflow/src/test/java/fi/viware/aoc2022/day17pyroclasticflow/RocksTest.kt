package fi.viware.aoc2022.day17pyroclasticflow

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class RocksTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun sameObjectOrNot(){
        val rocks = Rocks()
        val rock1 = rocks.dropNext()
        val rock2 = rocks.dropNext()
        val rock3 = rocks.dropNext()
        val rock4 = rocks.dropNext()
        val rock5 = rocks.dropNext()
        val rock6 = rocks.dropNext()
        val rock7 = rocks.dropNext()
        val rock8 = rocks.dropNext()
        val rock9 = rocks.dropNext()
        val rock10 = rocks.dropNext()

        assertEquals(true,rock1.equals(rock6))
        assertEquals(true,rock2.equals(rock7))
        assertEquals(true,rock5 == rock10) // == is the same as equals(). For data class it compares content, not reference
    }
}