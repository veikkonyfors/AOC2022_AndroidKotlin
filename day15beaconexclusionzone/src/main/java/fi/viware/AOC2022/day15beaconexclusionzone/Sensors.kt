package fi.viware.AOC2022.day15beaconexclusionzone

import java.io.File

/**
 * Is in charge of the spread out sensors.
 * Keeps list of the sensors.
 * Also keeps track of points where a beacon can' exist, in the form of list noBeacons.
 * Logic is based on each sensors nearest beacon limiting another beacon to exist closer to that sensor.
 */
class Sensors(val sensorDataLines: List<String>) {

    var sensors: MutableList<Sensor> = mutableListOf()
    var noBeacons: MutableList<Point> = mutableListOf()

    init {
        parseSensorDataLines()
    }

    /**
     * Parses sensor data lines, adding sensors with nearest beacon to the list of sensors.
     * Also maintains the list of points no Beacons can exist at.
     */
    fun parseSensorDataLines(){
        sensorDataLines.forEach {
            val sensorToAdd = SensorDataLine(it).toSensor()
            sensors.add(sensorToAdd)
            noBeacons = noBeacons.union(noBeaconPoints(sensorToAdd)).toMutableList()
        }
    }

    /**
     * Returns a list of points near the given sensor where a beacon can't exist, i.e. points
     * closer to it that than it's nearestBeacon.
     * Including sensor's and beacon's points themselves.
     */
    fun noBeaconPoints(sensor: Sensor): List<Point> {
        var noBeaconPoints = mutableListOf<Point>(sensor.pointSensor, sensor.pointBeacon)
        val manhattanDistance =
            sensor.pointSensor.manhattanDistance(sensor.pointBeacon)
        for (i in 1..manhattanDistance) {
            noBeaconPoints.add(Point(i, manhattanDistance - i))
        }
        return noBeaconPoints
    }

    override fun toString(): String {
        return sensors.toString()
    }
}