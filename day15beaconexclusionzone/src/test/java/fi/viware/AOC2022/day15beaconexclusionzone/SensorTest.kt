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

        var sensor:Sensor
        var r:String
        val listNoBeacons:MutableList<Point> = mutableListOf()

        sensor = SensorDataLine("Sensor at x=8, y=7: closest beacon is at x=2, y=10").toSensor()
        //println("${sensor.pointSensor}, ${sensor.pointBeacon} Distance ${sensor.pointSensor.manhattanDistance(sensor.pointBeacon)}")
       sensor.addNoBeaconPoints(10, listNoBeacons)
        r = listNoBeacons.toString()
        println(r)
        assertEquals("[(3, 10), (4, 10), (5, 10), (6, 10), (7, 10), (8, 10), (9, 10), (10, 10), (11, 10), (12, 10), (13, 10), (14, 10)]",r)

        sensor = SensorDataLine("Sensor at x=1, y=1: closest beacon is at x=2, y=2").toSensor()
        println("${sensor.pointSensor}, ${sensor.pointBeacon} Distance ${sensor.pointSensor.manhattanDistance(sensor.pointBeacon)}")
        sensor.addNoBeaconPoints(2, listNoBeacons)
        r = listNoBeacons.toString()
        //println(r)
        assertEquals("[(3, 10), (4, 10), (5, 10), (6, 10), (7, 10), (8, 10), (9, 10), (10, 10), (11, 10), (12, 10), (13, 10), (14, 10), (0, 2), (1, 2)]",r)

        sensor = SensorDataLine("Sensor at x=1, y=1: closest beacon is at x=2, y=2").toSensor()
        println("${sensor.pointSensor}, ${sensor.pointBeacon} Distance ${sensor.pointSensor.manhattanDistance(sensor.pointBeacon)}")
        sensor.addNoBeaconPoints(1, listNoBeacons)
        r = listNoBeacons.toString()
        //println(r)
        assertEquals("[(3, 10), (4, 10), (5, 10), (6, 10), (7, 10), (8, 10), (9, 10), (10, 10), (11, 10), (12, 10), (13, 10), (14, 10), (0, 2), (1, 2), (-1, 1), (0, 1), (1, 1), (2, 1), (3, 1)]",r)

    }

}