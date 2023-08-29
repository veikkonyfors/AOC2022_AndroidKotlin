package fi.viware.AOC2022.day14regolithreservoir

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class CaveTest {
    lateinit var pathLines: List<String>
    lateinit var cave: Cave
    @Before
    fun setUp() {
        val file = File(
            ".",
            "input_test"
        )
        pathLines = file.readLines()
        assertEquals("[498,4 -> 498,6 -> 496,6, 503,4 -> 502,4 -> 502,9 -> 494,9]",pathLines.toString())
        cave=Cave(pathLines)
    }

    @Test
    fun generatePaths() {
        // Moved generatePaths to init in Cave
        //cave.generatePaths()
        println(cave.paths)
        assertEquals("[[498, 4 ->, 498, 6 ->, 496, 6 ], [503, 4 ->, 502, 4 ->, 502, 9 ->, 494, 9 ]]",cave.paths.toString())
    }

    @Test
    fun generateRockPoints() {
        // Moved to init in Cave
        //cave.generatePaths()
        //cave.generateRockPoints()
        println(cave.rockPoints)
        assertEquals("[498, 4 ->, 498, 5 ->, 498, 6 ->, 497, 6 ->, 496, 6 ->, 503, 4 ->, 502, 4 ->, 502, 5 ->, 502, 6 ->, 502, 7 ->, 502, 8 ->, 502, 9 ->, 495, 9 ->, 496, 9 ->, 497, 9 ->, 498, 9 ->, 499, 9 ->, 500, 9 ->, 501, 9 ->, 494, 9 ->]",cave.rockPoints.toString())
        println("$cave.minX, $cave.maxX")
    }

    @Test
    fun findMinMaxX() {
        println("${cave.minX.toString()}, ${cave.maxX.toString()}")
        assertEquals("494, 503","${cave.minX.toString()}, ${cave.maxX.toString()}")
    }

    @Test
    fun findMinMaxY() {
        println("${cave.minY.toString()}, ${cave.maxY.toString()}")
        assertEquals("4, 9","${cave.minY.toString()}, ${cave.maxY.toString()}")
    }

    @Test
    fun occupiedPoints() {
        println(cave.occupiedPoints)
        assertEquals("[498, 4 ->, 498, 5 ->, 498, 6 ->, 497, 6 ->, 496, 6 ->, 503, 4 ->, 502, 4 ->, 502, 5 ->, 502, 6 ->, 502, 7 ->, 502, 8 ->, 502, 9 ->, 495, 9 ->, 496, 9 ->, 497, 9 ->, 498, 9 ->, 499, 9 ->, 500, 9 ->, 501, 9 ->, 494, 9 ->]",cave.occupiedPoints.toString())
        // Verify rockPoints and occupiedPoints have cloned points after init
        cave.rockPoints.forEachIndexed { i, p ->
            assertEquals(true,p == cave.occupiedPoints[i])
        }
    }

    @Test
    fun drop() {
        val pathLines = listOf("498,4 -> 498,6 -> 496,6","503,4 -> 502,4 -> 502,9 -> 494,9")
        val cave = Cave(pathLines)
        val rockPoints ="[498, 4 ->, 498, 5 ->, 498, 6 ->, 497, 6 ->, 496, 6 ->, 503, 4 ->, 502, 4 ->, 502, 5 ->, 502, 6 ->, 502, 7 ->, 502, 8 ->, 502, 9 ->, 495, 9 ->, 496, 9 ->, 497, 9 ->, 498, 9 ->, 499, 9 ->, 500, 9 ->, 501, 9 ->, 494, 9 ->]"
        cave.drop(SandGrain(Point(500,0)))
        println(cave.occupiedPoints)
        assertEquals(rockPoints.dropLast(1)+", 500, 8 ->]",cave.occupiedPoints.toString())
        println(cave.sandPoints)
        assertEquals("[500, 8 ->]",cave.sandPoints.toString())
        assertEquals(rockPoints,cave.rockPoints.toString())
        cave.drop(SandGrain(Point(500,0)))
        println(cave.occupiedPoints)
        assertEquals(rockPoints.dropLast(1)+", 500, 8 ->, 499, 8 ->]",cave.occupiedPoints.toString())
        println(cave.sandPoints)
        assertEquals("[500, 8 ->, 499, 8 ->]",cave.sandPoints.toString())
        assertEquals(rockPoints,cave.rockPoints.toString())
        cave.drop(SandGrain(Point(500,0)))
        println(cave.occupiedPoints)
        assertEquals(rockPoints.dropLast(1)+", 500, 8 ->, 499, 8 ->, 501, 8 ->]",cave.occupiedPoints.toString())
        println(cave.sandPoints)
        assertEquals("[500, 8 ->, 499, 8 ->, 501, 8 ->]",cave.sandPoints.toString())
        assertEquals(rockPoints,cave.rockPoints.toString())
        cave.drop(SandGrain(Point(500,0)))
        println(cave.occupiedPoints)
        assertEquals(rockPoints.dropLast(1)+", 500, 8 ->, 499, 8 ->, 501, 8 ->, 500, 7 ->]",cave.occupiedPoints.toString())
        println(cave.sandPoints)
        assertEquals("[500, 8 ->, 499, 8 ->, 501, 8 ->, 500, 7 ->]",cave.sandPoints.toString())
        assertEquals(rockPoints,cave.rockPoints.toString())

    }

    @Test
    fun fill() {
        val pathLines = listOf("498,4 -> 498,6 -> 496,6","503,4 -> 502,4 -> 502,9 -> 494,9")
        val cave = Cave(pathLines)
        cave.fill()
        println(cave.sandPoints.toString())
        println(cave.sandPoints.size)
    }

    @Test
    fun solvePuzz1() {
        val file = File(
            ".",
            "input"
        )
        pathLines = file.readLines()
        val cave = Cave(pathLines)
        cave.fill()
        println(cave.sandPoints.toString())
        println(cave.sandPoints.size)
    }

    //@Test
    fun solvePuzz2() {
        val file = File(
            ".",
            "input_test"
        )
        pathLines = file.readLines()
        val cave = CavePuzz2(pathLines)
        cave.fill()
        println(cave.sandPoints.toString())
        println(cave.sandPoints.size)
    }


    @After
    fun tearDown() {
    }

    @Test
    fun getRockPoints() {
    }
}