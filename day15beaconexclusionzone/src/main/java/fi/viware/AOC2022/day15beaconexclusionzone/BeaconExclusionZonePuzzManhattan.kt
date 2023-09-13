package fi.viware.AOC2022.day15beaconexclusionzone

/**
 * This is for Puzz2!
 * Plain manhattan version. No inheritance!
 * Is in charge of the spread out sensors.
 * Keeps list of the sensors.
 * Also keeps track of points where a beacon can' exist, in the form of set noBeacons.
 * Logic is based on each sensors nearest beacon limiting another beacon to exist closer to that sensor.
 */

// Inherited version. lineOfInterest
class BeaconExclusionZonePuzzManhattan(val sensorDataLines: List<String>) {

    var sensors: MutableList<Sensor> = mutableListOf()
    var availableBeaconPoints: Set<Point> = mutableSetOf()

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
        }
    }

    /**
     * Scans through all surrounding points of each sensor and checks whether it is available.
     * Return all available points found from the shrinked nobeacons.
     */
    fun availableBeaconPoints_surroundingpoints_manhattandistance_impl(low: Int, high: Int): MutableSet<Point> {

        val availableBeaconPoints = mutableSetOf<Point>()

        sensors.forEach { s->
            val surroundingPoints = s.surroundingPoints()
            surroundingPoints.forEach { p->
                if(p.x !in low .. high || p.y !in low .. high) return@forEach
                // This surrounding point on one of the diamonds need to be checked againg each
                // sensor to be outside of it's range
                var farAway = true
                sensors.forEach { s2->
                    if (p.manhattanDistance(s2.pointSensor) <= s2.manhattanDistance) {
                        farAway = false
                    } else {
                        val x = 0
                        //println("Outer loop s: $s, it's surrounding p: $p, Inner loop s: $s2, p.manhattanDistance(s2.pointSensor) ${p.manhattanDistance(s2.pointSensor)}")
                    }
                }
                if (farAway) {
                    availableBeaconPoints.add(p)
                }
            }
        }
        return availableBeaconPoints
    }

    override fun toString(): String {
        return sensors.toString()
    }
}