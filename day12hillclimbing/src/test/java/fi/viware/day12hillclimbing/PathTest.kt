package fi.viware.day12hillclimbing

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.*

class PathTest {

    lateinit var hillHeight: List<String>
    lateinit var hill:Hill

    @Before
    fun setUp() {
        val file = File(
            ".",
            "input"
        )
        hillHeight = file.readLines()
        hill = Hill(hillHeight)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun test1stPuzzle() {

        println(hill.cost)
        println(hill.hillHeigths)
        while(hill.carryOn){
            hill.iterate()
        }

        hill.cost.forEach { println(Arrays.deepToString(it)) }
        hill.hillHeigths.forEach { println(it) }


        assertEquals(hillHeight[0][0],'S')
        assertEquals(hillHeight[2][5],'E')
    }

    @Test
    fun testFindStartAndEnd() {
        hill.findStartAndEnd()
        assertEquals(hill.startRow, 0)
        assertEquals(hill.startCol, 0)
        assertEquals(hill.endRow, 2)
        assertEquals(hill.endCol, 5)
    }
}