package fi.viware.AOC2022.day15beaconexclusionzone

import java.io.File

/**
 * Is in charge of the spread out sensors.
 */
class Sensors(val sensorDataLines: List<String>) {

    var sensors: MutableList<Sensor> = mutableListOf()

    init {
        parseSensorDataLines()
    }

    /**
     * Parses sensor data lines, adding sensors with nearest beacon to the list of sensors
     */
    fun parseSensorDataLines(){
        sensorDataLines.forEach {
            //Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            //Sensor at x=9, y=16: closest beacon is at x=10: closest beacon is at x=16

            val (sx, sy, bx, by) =
                it.replace("Sensor at x=", "")
                    .replace(", y=",",")
                    .replace(": closest beacon is at x=",",")
                    .replace(", y=",",")
                    .split(",")
            //println("$sx, $sy, $bx, $by")

            sensors.add(Sensor(Point(sx.toInt(),sy.toInt()),Point(bx.toInt(), by.toInt())))
        }
    }

    override fun toString(): String {
        return sensors.toString()
    }
}