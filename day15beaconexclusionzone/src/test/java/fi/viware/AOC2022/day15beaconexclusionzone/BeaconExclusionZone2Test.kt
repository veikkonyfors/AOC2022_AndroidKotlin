package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.math.BigInteger

class BeaconExclusionZone2Test {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun solvePuzz2_test_set_way_w_avlbcns_3parameters_BF() {
        val input = File(".", "input_test")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(816,beaconExclusionZone.noBeacons.size)
        beaconExclusionZone.noBeacons = beaconExclusionZone.shrinkNoBeacons(beaconExclusionZone.noBeacons, 0, 20)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(440,beaconExclusionZone.noBeacons.size)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints(beaconExclusionZone.noBeacons, 0, 20)
        println(availableBeaconPoints)
        assertEquals("[(14, 11)]",availableBeaconPoints.toString())
    }

    @Test
    fun solvePuzz2_test_set_way_w_avlbcns_2parameters_BF() {
        val input = File(".", "input_test")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(816,beaconExclusionZone.noBeacons.size)
        // NOTE: No shrinkNoBeacons required, as we scan BF lowxhigh grid against manhattan distance.
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints(0, 20)
        println(availableBeaconPoints)
        assertEquals("[(14, 11)]",availableBeaconPoints.toString())
    }

    /**
     * Find out under which sensor (0,8) in covered
     */
    @Test
    fun find0_8() {
        var sensorDataLines = listOf<String>(
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

        var beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        beaconExclusionZone.sensors.forEach {
            if (it.pointSensor.manhattanDistance(Point(0,8)) > it.manhattanDistance) println(it)
            println("$it, manhattandistance ${it.manhattanDistance}, ${it.pointSensor} ${Point(0,8)} ${it.pointSensor.manhattanDistance(Point(0,8))}")
        }
    }

    @Test
    fun availableBeaconPoints_2par_1line() {
        var sensorDataLines = listOf<String>(
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
        )
        var beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        println(beaconExclusionZone.noBeacons)

        var availableBeaconPoints = beaconExclusionZone.availableBeaconPoints(0, 20)

        println(availableBeaconPoints.toString())
        assertEquals("[(0, 0), (0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 9), (0, 10), (0, 11), (0, 12), (0, 13), (0, 14), (0, 15), (0, 16), (0, 17), (0, 18), (0, 19), (0, 20), (1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20), (2, 0), (2, 1), (2, 2), (2, 3), (2, 11), (2, 12), (2, 13), (2, 14), (2, 15), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20), (3, 0), (3, 1), (3, 2), (3, 12), (3, 13), (3, 14), (3, 15), (3, 16), (3, 17), (3, 18), (3, 19), (3, 20), (4, 0), (4, 1), (4, 13), (4, 14), (4, 15), (4, 16), (4, 17), (4, 18), (4, 19), (4, 20), (5, 0), (5, 14), (5, 15), (5, 16), (5, 17), (5, 18), (5, 19), (5, 20), (6, 15), (6, 16), (6, 17), (6, 18), (6, 19), (6, 20), (7, 16), (7, 17), (7, 18), (7, 19), (7, 20), (8, 17), (8, 18), (8, 19), (8, 20), (9, 16), (9, 17), (9, 18), (9, 19), (9, 20), (10, 15), (10, 16), (10, 17), (10, 18), (10, 19), (10, 20), (11, 0), (11, 14), (11, 15), (11, 16), (11, 17), (11, 18), (11, 19), (11, 20), (12, 0), (12, 1), (12, 13), (12, 14), (12, 15), (12, 16), (12, 17), (12, 18), (12, 19), (12, 20), (13, 0), (13, 1), (13, 2), (13, 12), (13, 13), (13, 14), (13, 15), (13, 16), (13, 17), (13, 18), (13, 19), (13, 20), (14, 0), (14, 1), (14, 2), (14, 3), (14, 11), (14, 12), (14, 13), (14, 14), (14, 15), (14, 16), (14, 17), (14, 18), (14, 19), (14, 20), (15, 0), (15, 1), (15, 2), (15, 3), (15, 4), (15, 10), (15, 11), (15, 12), (15, 13), (15, 14), (15, 15), (15, 16), (15, 17), (15, 18), (15, 19), (15, 20), (16, 0), (16, 1), (16, 2), (16, 3), (16, 4), (16, 5), (16, 9), (16, 10), (16, 11), (16, 12), (16, 13), (16, 14), (16, 15), (16, 16), (16, 17), (16, 18), (16, 19), (16, 20), (17, 0), (17, 1), (17, 2), (17, 3), (17, 4), (17, 5), (17, 6), (17, 8), (17, 9), (17, 10), (17, 11), (17, 12), (17, 13), (17, 14), (17, 15), (17, 16), (17, 17), (17, 18), (17, 19), (17, 20), (18, 0), (18, 1), (18, 2), (18, 3), (18, 4), (18, 5), (18, 6), (18, 7), (18, 8), (18, 9), (18, 10), (18, 11), (18, 12), (18, 13), (18, 14), (18, 15), (18, 16), (18, 17), (18, 18), (18, 19), (18, 20), (19, 0), (19, 1), (19, 2), (19, 3), (19, 4), (19, 5), (19, 6), (19, 7), (19, 8), (19, 9), (19, 10), (19, 11), (19, 12), (19, 13), (19, 14), (19, 15), (19, 16), (19, 17), (19, 18), (19, 19), (19, 20), (20, 0), (20, 1), (20, 2), (20, 3), (20, 4), (20, 5), (20, 6), (20, 7), (20, 8), (20, 9), (20, 10), (20, 11), (20, 12), (20, 13), (20, 14), (20, 15), (20, 16), (20, 17), (20, 18), (20, 19), (20, 20)]",availableBeaconPoints.toString())


    }
    @Test
    fun availableBeaconPoints_avlbcns_2parameters_lines_test() {

        var sensorDataLines = listOf<String>(
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

        var beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)

        var availableBeaconPoints = beaconExclusionZone.availableBeaconPoints(0, 20)

        println(availableBeaconPoints.toString())
        assertEquals("[(14, 11)]",availableBeaconPoints.toString())
    }

    @Test
    fun availableBeaconPoints_avlbcns_surroundingpoints_impl_1line() {

        var sensorDataLines = listOf<String>(
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
        )

        val beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(181,beaconExclusionZone.noBeacons.size)
        beaconExclusionZone.noBeacons = beaconExclusionZone.shrinkNoBeacons(beaconExclusionZone.noBeacons, 0, 20)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(176,beaconExclusionZone.noBeacons.size)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_impl(0, 20)
        // Should not be the same as surroundingPoints points outside 0 .. 20 removed
        val strAvailableBeaconPoints = availableBeaconPoints.toString()
        val strSurroundingPoints = beaconExclusionZone.sensors[0].surroundingPoints().toString()
        println("strSurroundingPoints:  $strSurroundingPoints")
        println("AvailableBeaconPoints: $strAvailableBeaconPoints")
        println("AvailableBeaconPoints: [(0, 9), (0, 5), (1, 10), (1, 4), (2, 11), (2, 3), (3, 12), (3, 2), (4, 13), (4, 1), (5, 14), (5, 0), (6, 15), (6, -1), (7, 16), (7, -2), (8, 17), (8, -3), (9, 16), (9, -2), (10, 15), (10, -1), (11, 14), (11, 0), (12, 13), (12, 1), (13, 12), (13, 2), (14, 11), (14, 3), (15, 10), (15, 4), (16, 9), (16, 5), (17, 8), (17, 6), (18, 7)]")
        assertEquals("[(0, 9), (0, 5), (1, 10), (1, 4), (2, 11), (2, 3), (3, 12), (3, 2), (4, 13), (4, 1), (5, 14), (5, 0), (6, 15), (7, 16), (8, 17), (9, 16), (10, 15), (11, 14), (11, 0), (12, 13), (12, 1), (13, 12), (13, 2), (14, 11), (14, 3), (15, 10), (15, 4), (16, 9), (16, 5), (17, 8), (17, 6), (18, 7)]",availableBeaconPoints.toString())
    }

    @Test
    fun availableBeaconPoints_test_surroundingpoints_impl_input_test() {
        val input = File(".", "input_test")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(816,beaconExclusionZone.noBeacons.size)
        //beaconExclusionZone.noBeacons = beaconExclusionZone.shrinkNoBeacons(beaconExclusionZone.noBeacons, 0, 20)
        //println(beaconExclusionZone.noBeacons.size)
        //assertEquals(440,beaconExclusionZone.noBeacons.size)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_impl(0, 20)
        println(availableBeaconPoints)
        assertEquals("[(14, 11)]",availableBeaconPoints.toString())
    }

    //@Test SLOW
    fun availableBeaconPoints_surroundingpoints_impl_input() {
        val input = File(".", "input")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(816,beaconExclusionZone.noBeacons.size)
        //beaconExclusionZone.noBeacons = beaconExclusionZone.shrinkNoBeacons(beaconExclusionZone.noBeacons, 0, 20)
        //println(beaconExclusionZone.noBeacons.size)
        //assertEquals(440,beaconExclusionZone.noBeacons.size)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_impl(0, 20)
        println(availableBeaconPoints)
        assertEquals("[(14, 11)]",availableBeaconPoints.toString())
    }


    @Test
    fun availableBeaconPoints_surroundingpoints_manhattandistance_impl_1line() {

        var sensorDataLines = listOf<String>(
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
        )

        val beaconExclusionZone = BeaconExclusionZonePuzz2(sensorDataLines)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(181,beaconExclusionZone.noBeacons.size)
        beaconExclusionZone.noBeacons = beaconExclusionZone.shrinkNoBeacons(beaconExclusionZone.noBeacons, 0, 20)
        println(beaconExclusionZone.noBeacons.size)
        assertEquals(176,beaconExclusionZone.noBeacons.size)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_manhattandistance_impl(0, 20)
        // Should not be the same as surroundingPoints points outside 0 .. 20 removed
        val strAvailableBeaconPoints = availableBeaconPoints.toString()
        val strSurroundingPoints = beaconExclusionZone.sensors[0].surroundingPoints().toString()
        println("strSurroundingPoints:  $strSurroundingPoints")
        println("AvailableBeaconPoints: $strAvailableBeaconPoints")
        assertEquals("[(0, 9), (0, 5), (1, 10), (1, 4), (2, 11), (2, 3), (3, 12), (3, 2), (4, 13), (4, 1), (5, 14), (5, 0), (6, 15), (7, 16), (8, 17), (9, 16), (10, 15), (11, 14), (11, 0), (12, 13), (12, 1), (13, 12), (13, 2), (14, 11), (14, 3), (15, 10), (15, 4), (16, 9), (16, 5), (17, 8), (17, 6), (18, 7)]",availableBeaconPoints.toString())
    }


    @Test
    fun availableBeaconPoints_surroundingpoints_manhattandistance_impl_input_test() {
        val input = File(".", "input_test")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzzManhattan(sensorDataLines)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_manhattandistance_impl(0, 20)
        println(availableBeaconPoints)
        assertEquals("[(14, 11)]",availableBeaconPoints.toString())
    }

    @Test
    fun availableBeaconPoints_surroundingpoints_manhattandistance_impl_input() {
        val input = File(".", "input")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzzManhattan(sensorDataLines)
        val availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_manhattandistance_impl(0, 4000000)
        println(availableBeaconPoints)
        println(3293021*4000000 + 3230812)
        assertEquals("[(3293021, 3230812)]",availableBeaconPoints.toString())
    }

    @Test
    fun solvePuzz2() {
        val input = File(".", "input")
        val sensorDataLines = input.readLines()
        var beaconExclusionZone = BeaconExclusionZonePuzzManhattan(sensorDataLines)
        var availableBeaconPoints = beaconExclusionZone.availableBeaconPoints_surroundingpoints_manhattandistance_impl(0, 4000000)
        println(availableBeaconPoints.toString())
        assertEquals(1,availableBeaconPoints.size)
        val point = availableBeaconPoints.first()
        val X = point.x.toBigInteger()
        val Y = point.y.toBigInteger()
        val solution = X*BigInteger.valueOf(4000000) + Y
        println("$point, $solution")
        assertEquals(BigInteger.valueOf(13172087230812),solution)
    }

    /*
    Bit of history:

    puzz1 makes use of BeaconExclusionZone.
    initially nobeacons was a full List of points. SLOW and HEAP full
    Changed it to Set. A bit better, but still SLOW & HEAP
    Changed noBeacons to only hold lineOfInterest. OK

    Puzz2.
    Inherited BeaconExclusionZonePuzz2 from BeaconExclusionZone.
    generateNoBeaconPoints generated a full Set.
    Had 2 and 3 parameter versions of availableBeaconPoints, latter used manhattandistance instead
    of noBeacons. No luck with full input.
    availableBeaconPoints_surroundingpoints_impl and availableBeaconPoints_surroundingpoints_manhattandistance_impl,
    SLOW with full input.

    Eventually availableBeaconPoints_surroundingpoints_manhattandistance_impl solved full input as well.
    It goes through each and all surrounding points of each sensor and checks if point isn't covered by
    any of the sensor's manhattandistance.

    Huh Huh.

     */

}