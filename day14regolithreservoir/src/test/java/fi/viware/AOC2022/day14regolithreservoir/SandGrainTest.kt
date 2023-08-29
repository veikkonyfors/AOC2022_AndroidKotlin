package fi.viware.AOC2022.day14regolithreservoir

import org.junit.Assert.*

import org.junit.Test

class SandGrainTest {

    @Test
    fun down() {
        val sandGrain = SandGrain(Point(5,6))
        println(sandGrain.down().toString())
        assertEquals("5, 7 ->",sandGrain.down().toString())
    }

    @Test
    fun goDown() {
        val sandGrain = SandGrain(Point(9,0))
        sandGrain.goDown()
        println(sandGrain)
        assertEquals("9, 1 ->",sandGrain.toString())
    }

    @Test
    fun downLeft() {
        val sandGrain = SandGrain(Point(7,7))
        println(sandGrain.downLeft().toString())
        assertEquals("6, 8 ->",sandGrain.downLeft().toString())
    }

    @Test
    fun goDownLeft() {
        val sandGrain = SandGrain(Point(8,5))
        sandGrain.goDownLeft()
        println(sandGrain)
        assertEquals("7, 6 ->",sandGrain.toString())
    }

    @Test
    fun downRight() {
        val sandGrain = SandGrain(Point(8,7))
        println(sandGrain.downRight().toString())
        assertEquals("9, 8 ->",sandGrain.downRight().toString())
    }

    @Test
    fun goDownRight() {
        val sandGrain = SandGrain(Point(9,8))
        sandGrain.goDownRight()
        println(sandGrain)
        assertEquals("10, 9 ->",sandGrain.toString())
    }
}