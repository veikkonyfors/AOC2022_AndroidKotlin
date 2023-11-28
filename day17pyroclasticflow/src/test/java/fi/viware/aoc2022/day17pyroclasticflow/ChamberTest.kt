package fi.viware.aoc2022.day17pyroclasticflow

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ChamberTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun drop1(){
        // As drop height is 4, each dropped rock consumes 4 arrows on the string
        val chamber = Chamber(">>>> <<<< >><>") //>>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
        var rock = Rock(mutableListOf(listOf(), listOf(), listOf(0), listOf(0), listOf(0), listOf(0), listOf()))
        chamber.drop(rock)
        println("chamber:\n$chamber")
        assertEquals("|#######|\n|   ####|\n", chamber.toString())

        rock = Rock(mutableListOf(listOf(), listOf(), listOf(0), listOf(0), listOf(0), listOf(0), listOf()))
        chamber.drop(rock)
        println("chamber:\n$chamber")
        assertEquals("|#######|\n|   ####|\n|####   |\n", chamber.toString())

        rock = Rock(mutableListOf(emptyList(), emptyList(), listOf(0), listOf(0), listOf(0, 1, 2), emptyList(), emptyList()))
        chamber.drop(rock)
        println("chamber:\n$chamber")
        assertEquals("|#######|\n|   ####|\n|####   |\n|  ###  |\n|    #  |\n", chamber.toString())
    }

    @Test
    fun droploop(){
        val chamber = Chamber(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
        val rocks = Rocks()
        var highestPilePrevious = 0
        for (count in 1..2022) {
            chamber.drop(rocks.dropNext())
            val lowerOrNot = when(chamber.highestPile < highestPilePrevious) {
                true -> "lowerOrNot $chamber.highestPile < $highestPilePrevious"
                false -> ""
            }
            //println("round $count, chamber.highestPile ${chamber.highestPile}, $lowerOrNot")
            highestPilePrevious = chamber.highestPile
            //println("chamber:\n$chamber")
        }

        //println("chamber:\n$chamber")
        println("chamber.highestPile: ${chamber.highestPile}")
    }

    /**
     * Drop a few rocks to adjust collide and add as second cross rock was one slot left.
     * Had to add chamber.highestPile in collide and drop.
     */
    @Test
    fun dropFew() {

        val chamber = Chamber(">>>< <><> ><<<> ><>>><< <>>><<<><<<>><>><<>>")

        var rock = Rock(mutableListOf(emptyList(), emptyList(), listOf(0), listOf(0), listOf(0), listOf(0), emptyList()))
        println("dropped rock: \n$rock")
        chamber.drop(rock)
        println("chamber after drop: \n$chamber")

        rock = Rock(mutableListOf(emptyList(), emptyList(), listOf(1), listOf(0, 1, 2), listOf(1), emptyList(), emptyList()))
        println("dropped rock: \n$rock")
        chamber.drop(rock)
        println("chamber after drop: \n$chamber")

        rock = Rock(mutableListOf(emptyList(), emptyList(), listOf(0), listOf(0), listOf(0, 1, 2), emptyList(), emptyList()))
        println("dropped rock: \n$rock")
        chamber.drop(rock)
        println("chamber after drop: \n$chamber")

        rock = Rock(mutableListOf(emptyList(), emptyList(), listOf(0,1,2,3), emptyList(), emptyList(), emptyList(), emptyList()))
        println("dropped rock: \n$rock")
        chamber.drop(rock)
        println("chamber after drop: \n$chamber")


    }
}