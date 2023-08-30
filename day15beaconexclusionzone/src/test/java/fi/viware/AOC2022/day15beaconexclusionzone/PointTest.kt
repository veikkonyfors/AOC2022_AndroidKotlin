package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*
import org.junit.Test


class PointTest{
    @Test
    fun manhattanDistance() {
        assertEquals(9,Point(9, 7).manhattanDistance(Point(2, 10)))
        assertEquals(14,Point(12, 14).manhattanDistance(Point(-2, 15)))
    }
}