package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*
import org.junit.Test


class PointTest{
    @Test
    fun manhattanDistance() {
        assertEquals(9,Point(8, 7).manhattanDistance(Point(2, 10)))
        assertEquals(15,Point(12, 14).manhattanDistance(Point(-2, 15)))
        assertEquals(2,Point(-10, -10).manhattanDistance(Point(-11, -11)))
        assertEquals(3,Point(-10, -10).manhattanDistance(Point(-11, -12)))
    }
}