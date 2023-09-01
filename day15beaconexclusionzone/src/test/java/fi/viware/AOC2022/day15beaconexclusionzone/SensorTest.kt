package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class SensorTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun noBeaconPoints() {
        val sensor = SensorDataLine("Sensor at x=1, y=1: closest beacon is at x=2, y=2").toSensor()
        println(sensor.pointSensor.manhattanDistance(sensor.pointBeacon))
        println(sensor.noBeaconPoints().toString())

    }

}