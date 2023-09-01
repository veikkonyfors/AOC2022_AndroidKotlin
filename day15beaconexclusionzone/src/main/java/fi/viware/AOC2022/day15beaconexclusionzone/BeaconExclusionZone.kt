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
            val noBeaconsToAdd = sensorToAdd.noBeaconPoints()

            // 1.9.2023, VN: For some reason distictBy below skips one point, (1,10).
            //noBeacons = noBeacons.union(noBeaconsToAdd).toMutableList().distinctBy { it.x.toString() + it.y.toString() }.toMutableList()
            noBeacons = noBeacons.union(noBeaconsToAdd).toMutableList()
            if(sensorToAdd.pointSensor.x == 0 && sensorToAdd.pointSensor.y == 11) {
                println("noBeacons")
                printLine(noBeacons, 10)
            }

            //noBeacons = noBeacons.distinctBy {p -> p.x.toString() + p.y.toString() }.toMutableList()
            noBeacons = removeDuplicates(noBeacons)
            if(sensorToAdd.pointSensor.x == 0 && sensorToAdd.pointSensor.y == 11) {
                println("noBeacons distinct")
                printLine(noBeacons, 10)
            }
        }
    }

    fun NumNoBeaconPointsOnLine(line: Int):Int{
        var n:Int = 0
        noBeacons.forEach {
            if(it.y == line) { n+=1; println("$it, $n") }
        }

        return n
    }

    fun removeDuplicates(list: MutableList<Point>): MutableList<Point>{
        val uniqueList = mutableListOf<Point>()
        list.forEach { p->
            var exists = false
            uniqueList.forEach { pu ->
                if (p.equals(pu)) {exists = true; return@forEach}
            }
            if(! exists) uniqueList.add(p)
        }
        return uniqueList
    }
    fun printLine(list: MutableList<Point> ,line: Int){

        list.forEach {
            if(it.y == line) println("${it.toString()}")
        }
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