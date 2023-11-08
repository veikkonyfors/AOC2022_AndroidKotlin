package fi.viware.aoc2022.day17pyroclasticflow

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class JetPatternTest {

    @Before
    fun setUp() {
        //val inputFile = File(".","input")
        //val inputLines = inputFile.readLines()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun pushRepeat(){
        val jetPatternString = ">>><<><>><<<>><>>><<<>>><<<><<<><>><<>>"
        val jetPattern = JetPattern(jetPatternString)

        // Repeat it two times to verify repeating works ok
        repeat(2) {
            jetPatternString.forEach {
                val ret = jetPattern.push()
                println("push: $ret")
                assertEquals(
                    ret, when (it) {
                        '<' -> -1; '>' -> 1; else -> 0
                    }
                )
            }
        }
    }

    @Test
    fun pushSkip(){
        val jetPatternString = "> >>x <"
        val jetPattern = JetPattern(jetPatternString)

        var pushes = ""

        repeat(jetPatternString.replace("[^<>]".toRegex(), "").length) {
            pushes += jetPattern.push()
        }
        println(pushes)
        assertEquals(pushes, "111-1")

    }
}