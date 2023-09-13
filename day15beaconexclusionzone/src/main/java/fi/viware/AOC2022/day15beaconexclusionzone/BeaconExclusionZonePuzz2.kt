package fi.viware.AOC2022.day15beaconexclusionzone

/**
 * This is for Puzz2!
 * Is in charge of the spread out sensors.
 * Keeps list of the sensors.
 * Also keeps track of points where a beacon can' exist, in the form of set noBeacons.
 * Logic is based on each sensors nearest beacon limiting another beacon to exist closer to that sensor.
 */

// Inherited version. lineOfInterest
class BeaconExclusionZonePuzz2(sensorDataLines: List<String>): BeaconExclusionZone(sensorDataLines,0) {

    var availableBeaconPoints: Set<Point> = mutableSetOf()

    init {
        parseSensorDataLines()
        this.generateNoBeaconPoints()
    }

    /**
     * Generates point where no beacons exist on the line of interest
     * Note: Points with beacons are not removed as they were in puzz1
     */
    private fun generateNoBeaconPoints(){
        sensors.forEach { s->
            s.addNoBeaconPointsPuzz2(noBeacons)
        }
    }

    /**
     * Shrinks nobeacons set to points with x and y coordinates between low and high.
     */
    fun shrinkNoBeacons(noBeacons: MutableSet<Point>, low:Int, high:Int): MutableSet<Point> {
        val shrinkedNoBeacons: MutableSet<Point> = mutableSetOf()
        shrinkedNoBeacons.addAll(noBeacons)

        noBeacons.forEach{
            if( (it.x < low) or (it.x > high)) shrinkedNoBeacons.remove(it)
            if( (it.y < low) or (it.y > high)) shrinkedNoBeacons.remove(it)
        }
        return shrinkedNoBeacons
    }

    /**
     * Returns available points for a beacon in given range.
     * Based on Brute Force, scanning through the range
     * and see it exists in beaconpoints.
     * Works for test input, but heap get full for the real input.
     * fun availableBeaconPoints(low:Int, high:Int): MutableSet<Point> {
     * below uses different logic to bypass that problem.
     */
    fun availableBeaconPoints(noBeacons: MutableSet<Point>, low:Int, high:Int): MutableSet<Point> {
        val availableBeaconPoints: MutableSet<Point> = mutableSetOf()

        for(x in low .. high)
            for(y in low .. high) {
                val p = Point(x, y)
                if(! noBeacons.contains(p)) availableBeaconPoints.add(p)
            }
        return availableBeaconPoints
    }

    /**
     * Returns available beacon points on the given range.
     * Tests each point in range whether it is available for for a beacon from each sensor's point of view,
     * i.e. is it outside manhattan distance.
     * Works ok for test input, but for real 4Mx4M inputtakes ages.
     */
    fun availableBeaconPoints(low:Int, high:Int): MutableSet<Point> {

        val availableBeaconPoints = mutableSetOf<Point>()

        var available = true
        lateinit var p: Point

        // Try each point if they are within manhattan distance for any sensor
        for (i in low..high)
            for (j in low..high) {
                p = Point(i, j)
                available = true
                sensors.forEach {
                    val d1 = it.manhattanDistance
                    val d2 = p.manhattanDistance(it.pointSensor)
                    if (it.manhattanDistance >= p.manhattanDistance(it.pointSensor))
                        available = false
                    return@forEach
                }
                if (available) {
                    availableBeaconPoints.add(p)
                }

            }
        return availableBeaconPoints
   }

    /**
     * Scans through all surrounding points of each sensor and checks whether it is available.
     * Return all available points found from the shrinked nobeacons.
     * SLOW filling noBeacons set with full input. Useless.
     */
    fun availableBeaconPoints_surroundingpoints_impl(low: Int, high: Int): MutableSet<Point> {

        val availableBeaconPoints = mutableSetOf<Point>()

        sensors.forEach {
            println("Sensor: $it")
            val surroundingPoints = it.surroundingPoints()
            surroundingPoints.forEach {
                if(it.x !in low .. high || it.y !in low .. high) return@forEach
                if (! noBeacons.contains(it)) availableBeaconPoints.add(it)
            }
        }
        return availableBeaconPoints
    }

    /**
     * Scans through all surrounding points of each sensor and checks whether it is available.
     * Return all available points found from the shrinked nobeacons.
     */
    fun availableBeaconPoints_surroundingpoints_manhattandistance_impl(low: Int, high: Int): MutableSet<Point> {

        val availableBeaconPoints = mutableSetOf<Point>()

        sensors.forEach { s->
            println("Sensor: $s")
            val surroundingPoints = s.surroundingPoints()
            surroundingPoints.forEach { p->
                if(p.x !in low .. high || p.y !in low .. high) return@forEach
                // if this surrounding point (must be for some other sensor than the current one)
                // is further away from sensor than it's manhattandistance.
                if (p.manhattanDistance(s.pointSensor) > s.manhattanDistance) availableBeaconPoints.add(p)
            }
        }
        return availableBeaconPoints
    }

    override fun toString(): String {
        return sensors.toString()
    }
}