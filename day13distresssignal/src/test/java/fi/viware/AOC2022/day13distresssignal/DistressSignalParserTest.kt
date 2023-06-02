package fi.viware.AOC2022.day13distresssignal

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class DistressSignalParserTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testParse(){
        var parser = DistressSignalParser("[[1],2,3]")
        var list:List<Any> = parser.parse(parser.listLine.toMutableList())
        println(list)

        DistressSignalParser("[1,1,3,1,1]").parse().also { it -> println(it)}

        DistressSignalParser("[[1],[2,3,4]]").parse().also { it -> println(it)}

        DistressSignalParser("[[1],4]").parse().also { it -> println(it)}
    }
}