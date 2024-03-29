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
        val listNoBeacons:MutableSet<Point> = mutableSetOf()

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

        sensor = SensorDataLine("Sensor at x=1, y=1: closest beacon is at x=2, y=1").toSensor()
        println("${sensor.pointSensor}, ${sensor.pointBeacon} Distance ${sensor.pointSensor.manhattanDistance(sensor.pointBeacon)}")
        sensor.addNoBeaconPoints(1, listNoBeacons)
        r = listNoBeacons.toString()
        //println(r)
        assertEquals("[(3, 10), (4, 10), (5, 10), (6, 10), (7, 10), (8, 10), (9, 10), (10, 10), (11, 10), (12, 10), (13, 10), (14, 10), (0, 2), (1, 2), (-1, 1), (0, 1), (1, 1), (2, 1), (3, 1)]",r)

    }

    @Test
    fun noBeaconPointsPuzz2() {

        var sensor: Sensor
        var r: String
        val listNoBeacons: MutableSet<Point> = mutableSetOf()

        sensor = SensorDataLine("Sensor at x=8, y=7: closest beacon is at x=2, y=10").toSensor()
        //println("${sensor.pointSensor}, ${sensor.pointBeacon} Distance ${sensor.pointSensor.manhattanDistance(sensor.pointBeacon)}")
        sensor.addNoBeaconPointsPuzz2(listNoBeacons)
        r = listNoBeacons.toString()
        println(r)
        assertEquals(
            "[(-1, 7), (0, 6), (0, 7), (0, 8), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10), (3, 11), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (4, 10), (4, 11), (4, 12), (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7), (5, 8), (5, 9), (5, 10), (5, 11), (5, 12), (5, 13), (6, 0), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6), (6, 7), (6, 8), (6, 9), (6, 10), (6, 11), (6, 12), (6, 13), (6, 14), (7, -1), (7, 0), (7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 7), (7, 8), (7, 9), (7, 10), (7, 11), (7, 12), (7, 13), (7, 14), (7, 15), (8, -2), (8, -1), (8, 0), (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6), (8, 7), (8, 8), (8, 9), (8, 10), (8, 11), (8, 12), (8, 13), (8, 14), (8, 15), (8, 16), (9, -1), (9, 0), (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 7), (9, 8), (9, 9), (9, 10), (9, 11), (9, 12), (9, 13), (9, 14), (9, 15), (10, 0), (10, 1), (10, 2), (10, 3), (10, 4), (10, 5), (10, 6), (10, 7), (10, 8), (10, 9), (10, 10), (10, 11), (10, 12), (10, 13), (10, 14), (11, 1), (11, 2), (11, 3), (11, 4), (11, 5), (11, 6), (11, 7), (11, 8), (11, 9), (11, 10), (11, 11), (11, 12), (11, 13), (12, 2), (12, 3), (12, 4), (12, 5), (12, 6), (12, 7), (12, 8), (12, 9), (12, 10), (12, 11), (12, 12), (13, 3), (13, 4), (13, 5), (13, 6), (13, 7), (13, 8), (13, 9), (13, 10), (13, 11), (14, 4), (14, 5), (14, 6), (14, 7), (14, 8), (14, 9), (14, 10), (15, 5), (15, 6), (15, 7), (15, 8), (15, 9), (16, 6), (16, 7), (16, 8), (17, 7)]",
            r
        )
    }

    @Test
    fun surroundingPoints() {
        var sensor: Sensor
        sensor = SensorDataLine("Sensor at x=8, y=7: closest beacon is at x=2, y=10").toSensor()

        var r = sensor.surroundingPoints()
        println(r)
        assertEquals(
            "[(-2, 7), (-1, 8), (-1, 6), (0, 9), (0, 5), (1, 10), (1, 4), (2, 11), (2, 3), (3, 12), (3, 2), (4, 13), (4, 1), (5, 14), (5, 0), (6, 15), (6, -1), (7, 16), (7, -2), (8, 17), (8, -3), (9, 16), (9, -2), (10, 15), (10, -1), (11, 14), (11, 0), (12, 13), (12, 1), (13, 12), (13, 2), (14, 11), (14, 3), (15, 10), (15, 4), (16, 9), (16, 5), (17, 8), (17, 6), (18, 7)]",
            r.toString()
        )
    }

}