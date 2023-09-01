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
        val sensors = BeaconExclusionZone(
            listOf<String>(
                "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16"
            )
        )
        println(sensors)
        assertEquals(
            "[Sensor((2, 18)), nearest Beacon((-2, 15)), Sensor((9, 16)), nearest Beacon((10, 16))]",
            sensors.toString()
        )
    }

    @Test
    fun NumNoBeaconPointsOnLine() {
        var sensors = BeaconExclusionZone(
            listOf<String>(
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
            ))

        //println(sensors.NumNoBeaconPointsOnLine(10))
        //println(sensors.noBeacons.toString())
        assertEquals(12,sensors.NumNoBeaconPointsOnLine(10))

        sensors = BeaconExclusionZone(
            listOf<String>(
                "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
                "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
                "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
                "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
                "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
                "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
                "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
                "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
                "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
                "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
                "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
                "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
            )
        )

        println(sensors.NumNoBeaconPointsOnLine(10))
        println(sensors.noBeacons.toString())
    }


}