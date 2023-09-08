package fi.viware.AOC2022.day15beaconexclusionzone

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

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
            ), 10)
        println(sensors)
        assertEquals(
            "[Sensor((2, 18)), nearest Beacon((-2, 15)), Sensor((9, 16)), nearest Beacon((10, 16))]",
            sensors.toString()
        )
    }

    @Test
    fun NumNoBeaconPointsOnLine() {
        var sensors: BeaconExclusionZone

        sensors = BeaconExclusionZone(
            listOf<String>(
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
            ), 10)

        println(sensors.noBeacons.size)
        println(sensors.noBeacons.toString())
        //assertEquals(12,sensors.noBeacons.size)


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
            ), 10
        )

        println(sensors.noBeacons.size)
        println(sensors.noBeacons.toString())
        assertEquals(26,sensors.noBeacons.size)
    }

    @Test
    fun solvePuzz1_test() {
        val input = File(".", "input_test")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZone(sensorDataLines, 10)
        //println(beaconExclusionZone.NumNoBeaconPointsOnLine(10))
        assertEquals(26,beaconExclusionZone.noBeacons.size)
    }


    /**
     * That's not the right answer; your answer is too high. If you're stuck, make sure you're using the full input data;
     * there are also some general tips on the about page, or you can ask for hints on the subreddit.
     * Please wait one minute before trying again. (You guessed 7038320.) [Return to Day 15]
     *
     * -5 as there is 5 beacons on line 2000000 per input.
     * removeBeaconPoints is too heavy, heap full.
     * But still:
     * That's not the right answer; your answer is too high.
     * If you're stuck, make sure you're using the full input data;
     * there are also some general tips on the about page, or you can ask for hints on the subreddit.
     * Please wait one minute before trying again. (You guessed 7038315.) [Return to Day 15]
     *
     * 8.9.2023:
     * That's the right answer! You are one gold star closer to collecting enough star fruit. [Continue to Part Two]
     * 5112035 - 1 First answered by decreasing the one beacon on lineofinterest by hand.
     * 5112034 Changed removeBeaconPoints to use set.remove() instead of set.add()
     */


    @Test
    fun solvePuzz1() {
        val input = File(".", "input")
        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZone(sensorDataLines, 2000000)
        //println(beaconExclusionZone.NumNoBeaconPointsOnLine(10))
        assertEquals(5112034,beaconExclusionZone.noBeacons.size)
    }

    /* 8.9.2023

12643*6985
88311355
88M!


25   12643   6822  ***
24   15760    221  ****
23   17910    244  ****
22   14843   5018  ****
21   22355   2953  *****
20   21476    986  *****
19   16883    917  ****
18   24712   4789  *****
17   20846   6117  ****
16   22472   5471  *****
15   38605   5613  *******
14   47733    932  ********
13   50098   1161  *********
12   57530   1013  **********
11   67062   8969  ************
10   82751   5275  *************
 9   80226  11051  **************
 8  104225   7271  *****************
 7  109798   2456  *****************
 6  150944   1512  ***********************
 5  152303   2900  ***********************
 4  177863   3443  ***************************
 3  192344  10546  ******************************
 2  222887  11267  **********************************
 1  266268  13108  *****************************************
     */

}