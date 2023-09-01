package fi.viware.AOC2022.day15beaconexclusionzone

/**
 * Is in charge of the spread out sensors.
 * Keeps list of the sensors.
 * Also keeps track of points where a beacon can' exist, in the form of list noBeacons.
 * Logic is based on each sensors nearest beacon limiting another beacon to exist closer to that sensor.
 */
class BeaconExclusionZone(val sensorDataLines: List<String>) {

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
            noBeacons = noBeacons.union(sensorToAdd.noBeaconPoints()).toMutableList()
        }
    }

    /**
     * Prints out given list of points.
     * Normally noBeacons is the list we are interested in.
     */
    fun printListOfPoints(){

    }

    override fun toString(): String {
        return sensors.toString()
    }
}