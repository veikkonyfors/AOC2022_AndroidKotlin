package fi.viware.AOC2022.day15beaconexclusionzone

/**
 * Is in charge of the spread out sensors.
 * Keeps list of the sensors.
 * Also keeps track of points where a beacon can' exist, in the form of list noBeacons.
 * Logic is based on each sensors nearest beacon limiting another beacon to exist closer to that sensor.
 */
class BeaconExclusionZone(val sensorDataLines: List<String>, val lineOfInterest:Int) {

    var sensors: MutableList<Sensor> = mutableListOf()
    var noBeacons: MutableSet<Point> = mutableSetOf()

    init {
        parseSensorDataLines()
        generateNoBeaconPoints()
    }

    /**
     * Parses sensor data lines, adding sensors with nearest beacon to the list of sensors.
     * Also maintains the list of points no Beacons can exist at.
     */
    fun parseSensorDataLines(){
        sensorDataLines.forEach {
            val sensorToAdd = SensorDataLine(it).toSensor()
            sensors.add(sensorToAdd)
        }
    }

    /**
     * Generates point where no beacons exist on the line of interest
     */
    private fun generateNoBeaconPoints(){
        sensors.forEach { s->
            s.addNoBeaconPoints(lineOfInterest, noBeacons)
            //noBeacons = noBeacons.union(noBeaconsWithThisSensor).toMutableList()
        }

        noBeacons = removeBeaconPoints(noBeacons)
    }


    /**
     * Removes nobeaconpoints where beacon exist for one or more sensors.
     * Even if beacon point for a sensor is not inserted while parsing a singlel sensor,
     * other sensors with other beacon may have added a nobeaconpoint there.
     * Needs to be removed before counting NumNoBeaconPointsOnLine.
     */
    fun removeBeaconPoints(list: MutableSet<Point>): MutableSet<Point>{

        var cutList = mutableSetOf<Point>()
        sensors.forEach { s->
            list.remove(s.pointBeacon)
        }
        return list
    }

    /**
     * Prints out given list of points as hashtags (#), like it is done in the puzz1 description..
     * Normally noBeacons is the list we are interested in.
     */
    fun printListOfPoints(){
        // To be implemented if required
    }

    override fun toString(): String {
        return sensors.toString()
    }
}