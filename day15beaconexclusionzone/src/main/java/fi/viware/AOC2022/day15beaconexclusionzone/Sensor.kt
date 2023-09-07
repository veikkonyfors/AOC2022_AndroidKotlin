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
    fun addNoBeaconPoints(lineofInterest:Int, listNoBeacons: MutableList<Point>){
        var noBeaconPoints = mutableListOf<Point>()
        val manhattanDistance =
            this.pointSensor.manhattanDistance(this.pointBeacon)
        println("manhattanDistance: $manhattanDistance")
        println("Adding sensor $this")
        val dx = abs(manhattanDistance - abs(pointSensor.y - lineofInterest))
        for (i in (-dx) .. (+dx)) {
            if (!(pointBeacon.x == pointSensor.x + i && pointBeacon.y == lineofInterest)) {
                // Do not add sensor's own beacon. Mainly for keeping test in tact.
                // Other sensors may add this point (or may have added already).
                // Beacon points need to be counted out eventually in the end when we count no beacon points on this line.
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