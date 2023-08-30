package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class SensorDataLineTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun toSensor() {
        var sensor =
            SensorDataLine("Sensor at x=2, y=18: closest beacon is at x=-2, y=15").toSensor()
        println(sensor)
        assertEquals("Sensor((2, 18)), nearest Beacon((-2, 15))", sensor.toString())
        sensor = SensorDataLine("Sensor at x=9, y=16: closest beacon is at x=10: closest beacon is at x=16").toSensor()
        println(sensor)
        assertEquals("Sensor((9, 16)), nearest Beacon((10, 16))", sensor.toString())
    }
}