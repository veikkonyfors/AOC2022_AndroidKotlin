package fi.viware.aoc2022.day17pyroclasticflow

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RockTest {
    @Before
    fun setUp() {
        //val inputFile = File(".","input")
        //val inputLines = inputFile.readLines()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun instantiate(){
        val rock1 = Rock(
            mutableListOf(listOf(), listOf(), listOf(0), listOf(0), listOf(0), listOf(0), listOf())
        )

        println("rock1: $rock1")
        assertEquals("|  #### |\n",rock1.toString())

        val rock2 = Rock(mutableListOf(emptyList(), emptyList(), listOf(1), listOf(0, 1, 2), listOf(1), emptyList(), emptyList()))

        println("rock2: \n$rock2")
        assertEquals(rock2.toString(), "|   #   |\n|  ###  |\n|   #   |\n")

        val rock3 = Rock(mutableListOf(emptyList(), emptyList(), listOf(0), listOf(0), listOf(0, 1, 2), emptyList(), emptyList()))

        println("rock3: \n$rock3")
        assertEquals(rock3.toString(), "|  ###  |\n|    #  |\n|    #  |\n")

        val rock4 = Rock(mutableListOf(emptyList(), emptyList(), listOf(0,1,2,3), emptyList(), emptyList(), emptyList(), emptyList()))

        println("rock4: \n$rock4")
        assertEquals(rock4.toString(), "|  #    |\n|  #    |\n|  #    |\n|  #    |\n")

        val rock5 = Rock(mutableListOf(emptyList(), emptyList(), listOf(0,1), listOf(0,1), emptyList(), emptyList(), emptyList()))

        println("rock5: \n$rock5"); assertEquals(rock5.toString(), "|  ##   |\n|  ##   |\n")

    }

    @Test
    fun moveHorizontally() {
        val rock1 = Rock(mutableListOf(listOf(), listOf(), listOf(0), listOf(0), listOf(0), listOf(0), listOf()))

        println("rock1: $rock1")
        rock1.moveHorizontally(-1)
        println("rock1: $rock1")
        assertEquals(rock1.toString(),"| ####  |\n")

        println("rock1: $rock1")
        rock1.moveHorizontally(-1)
        rock1.moveHorizontally(-1)
        rock1.moveHorizontally(-1)
        rock1.moveHorizontally(-1)
        println("rock1: $rock1")
        assertEquals(rock1.toString(),"|####   |\n")

        val rock2 = Rock(mutableListOf(emptyList(), emptyList(), listOf(1), listOf(0, 1, 2), listOf(1), emptyList(), emptyList()))
        println(rock2)
        rock2.moveHorizontally(1)
        println(rock2)
        assertEquals(rock2.toString(), "|    #  |\n|   ### |\n|    #  |\n")
    }

    @Test
    fun moveHorizontallyWithJetPattern() {

        val rock1 = Rock(mutableListOf(listOf(), listOf(), listOf(0), listOf(0), listOf(0), listOf(0), listOf()))

        var jetPatternString = ">>>" // ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>" input_test
        var jetPattern = JetPattern(jetPatternString)

        println("rock1: $rock1")
        rock1.moveHorizontally(jetPattern.push())
        rock1.moveHorizontally(jetPattern.push())
        rock1.moveHorizontally(jetPattern.push())
        println("rock1: $rock1")
        assertEquals(rock1.toString(),"|   ####|\n")

        jetPatternString = "<<<<" // ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>" input_test
        jetPattern = JetPattern(jetPatternString)
        println("rock1: $rock1")
        rock1.moveHorizontally(jetPattern.push())
        rock1.moveHorizontally(jetPattern.push())
        rock1.moveHorizontally(jetPattern.push())
        rock1.moveHorizontally(jetPattern.push())
        println("rock1: $rock1")
        assertEquals(rock1.toString(),"|####   |\n")
    }

    @Test
    fun down(){
        val rock = Rock(mutableListOf(listOf(), listOf(), listOf(0), listOf(0), listOf(0), listOf(0), listOf()))
        rock.down()
        println(rock.height)
        assertEquals(3, rock.height)
    }

    @Test
    fun highestPile(){

        var rock = Rock(mutableListOf(
            emptyList(),
            emptyList(),
            listOf(0),
            listOf(0),
            listOf(0, 1, 2),
            emptyList(),
            emptyList()
        ))

        println("highestPile: ${rock.highestPile()}")
        assertEquals(3, rock.highestPile())

        rock = Rock(mutableListOf(
            emptyList(),
            emptyList(),
            listOf(0,1,2,3),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        ))
        println("highestPile: ${rock.highestPile()}")
        assertEquals(4, rock.highestPile())
    }

    @Test
    fun justTest(){

        val rock2 = Rock(mutableListOf(
            emptyList(),
            emptyList(),
            listOf(1),
            listOf(0, 1, 2),
            listOf(1),
            emptyList(),
            emptyList()
        ))

        println("rock2: \n$rock2")
    }
}