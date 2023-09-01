package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class BeaconExclusionZoneTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun parseSensorDataLines() {
        val sensors = BeaconExclusionZone(listOf<String>("Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
            "Sensor at x=9, y=16: closest beacon is at x=10, y=16")
        )
        println(sensors)
        assertEquals("[Sensor((2, 18)), nearest Beacon((-2, 15)), Sensor((9, 16)), nearest Beacon((10, 16))]",sensors.toString())
    }



}