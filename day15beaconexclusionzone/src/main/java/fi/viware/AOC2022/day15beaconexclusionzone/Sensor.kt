package fi.viware.AOC2022.day15beaconexclusionzone

import java.lang.Math.abs

class Sensor(val pointSensor: Point, val pointBeacon: Point) {

    var nearestBeacon: Beacon = Beacon(pointBeacon)

    /**
     * Returns a list of points near this sensor where a beacon can't exist, i.e. points
     * closer to it that than it's nearestBeacon.
     * Including sensor itself and nearest beacon.
     * Need to do it only for the line of interest, getting HEAP full error if all lines involved.
     */
    fun addNoBeaconPoints(lineofInterest:Int, listNoBeacons: MutableSet<Point>){
        println("Adding sensor $this")
        val manhattanDistance =
            this.pointSensor.manhattanDistance(this.pointBeacon)
        if (manhattanDistance > abs(pointSensor.y - lineofInterest)) { // Only add if lineofinterest covered by manhattandistance
            val dx = abs(manhattanDistance - abs(pointSensor.y - lineofInterest))

            println("manhattanDistance: $manhattanDistance, firstX ${pointSensor.x - dx}, lastX ${pointSensor.x + dx}, count ${2*dx +1}")
            for (i in (-dx) .. (+dx)) {
                val p = Point(pointSensor.x + i, lineofInterest)
                if (! listNoBeacons.contains(p) and ! p.equals(nearestBeacon))
                    listNoBeacons.add(p)
            }
        }
    }

    override fun toString(): String {
        return "Sensor($pointSensor), nearest $nearestBeacon"
    }
}