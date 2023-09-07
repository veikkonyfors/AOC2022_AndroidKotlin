package fi.viware.AOC2022.day15beaconexclusionzone

/**
 * Is in charge of the spread out sensors.
 * Keeps list of the sensors.
 * Also keeps track of points where a beacon can' exist, in the form of list noBeacons.
 * Logic is based on each sensors nearest beacon limiting another beacon to exist closer to that sensor.
 */
class BeaconExclusionZone(val sensorDataLines: List<String>, val lineOfInterest:Int) {

    var sensors: MutableList<Sensor> = mutableListOf()
    var noBeacons: MutableList<Point> = mutableListOf()

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

            /*
            Still Heap full, even if nobeacons gathered only for line of interest.

            // Find out noBeaconPoints only for the line of interest. Not for all lines, as in original HEAP full version.
            val noBeaconsToAdd = sensorToAdd.noBeaconPoints(this.lineOfInterest)
            noBeacons = noBeacons.union(noBeaconsToAdd).toMutableList()
            noBeacons = removeDuplicates(noBeacons)
             */

            /*
            HEAP full version
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
            }*/
        }
    }

    /**
     * Generates point where no beacons exist on the line of interest
     */
    private fun generateNoBeaconPoints(){
        sensors.forEach { s->
            val noBeaconsWithThisSensor = s.addNoBeaconPoints(lineOfInterest, noBeacons)
            //noBeacons = noBeacons.union(noBeaconsWithThisSensor).toMutableList()
        }
        //noBeacons = removeDuplicates(noBeacons)
        noBeacons = removeBeaconPoints(noBeacons)
    }

    fun NumNoBeaconPointsOnLine(line: Int):Int{

        //noBeacons = removeBeaconPoints(noBeacons)

        var n:Int = 0
        noBeacons.forEach {
            if(it.y == line) {
                n+=1; //println("$it, $n")
            }
        }

        return n
    }

    fun removeDuplicates(list: MutableList<Point>): MutableList<Point>{
        var uniqueList = mutableListOf<Point>()
        uniqueList = list.distinctBy {p -> p.x.toString() + p.y.toString() }.toMutableList()

/*
        list.forEach { p->
            var exists = false
            uniqueList.forEach { pu ->
                if (p.equals(pu)) {exists = true; return@forEach}
            }
            if(! exists) uniqueList.add(p)
        }
 */
        return uniqueList
    }
    fun printLine(list: MutableList<Point> ,line: Int){

        list.forEach {
            if(it.y == line) println("${it.toString()}")
        }
    }

    /**
     * Removes nobeaconpoints where beacon exist for one or more sensors.
     * Even if beacon point for a sensor is not inserted while parsing a singlel sensor,
     * other sensors with other beacon may have added a nobeaconpoint there.
     * Needs to be removed before counting NumNoBeaconPointsOnLine.
     */
    fun removeBeaconPoints(list: MutableList<Point>): MutableList<Point>{

        var cutList = mutableListOf<Point>()

        noBeacons.forEach { nb->
            var exists = false
            sensors.forEach { s->
                if(nb.equals(s.pointBeacon)) exists = true
            }
            if(! exists) cutList.add(nb)
        }
        //cutList = removeDuplicates(cutList)
        return cutList
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