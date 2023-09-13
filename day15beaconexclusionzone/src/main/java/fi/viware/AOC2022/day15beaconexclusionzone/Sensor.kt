package fi.viware.AOC2022.day15beaconexclusionzone

import java.lang.Math.abs

open class Sensor(val pointSensor: Point, val pointBeacon: Point) {

    var nearestBeacon: Beacon = Beacon(pointBeacon)

    val manhattanDistance =
        this.pointSensor.manhattanDistance(this.pointBeacon)

    /**
     * Returns a list of points near this sensor where a beacon can't exist, i.e. points
     * closer to it that than it's nearestBeacon.
     * Including sensor itself and nearest beacon.
     * Need to do it only for the line of interest, getting HEAP full error if all lines involved.
     * Heap full took place with original list version of nobeacons. List had tobe changed to set later on anyway.
     * For puzz2 need to implement again a full nobeacons set with all lines, limited to the
     * area of interest though. Hopefully heap will not get full.
     */
    fun addNoBeaconPoints(lineofInterest:Int, listNoBeacons: MutableSet<Point>){
        //println("Adding sensor $this")
        val manhattanDistance =
            this.pointSensor.manhattanDistance(this.pointBeacon)
        if (manhattanDistance > abs(pointSensor.y - lineofInterest)) { // Only add if lineofinterest covered by manhattandistance
            val dx = abs(manhattanDistance - abs(pointSensor.y - lineofInterest))

            //println("manhattanDistance: $manhattanDistance, firstX ${pointSensor.x - dx}, lastX ${pointSensor.x + dx}, count ${2*dx +1}")
            for (i in (-dx) .. (+dx)) {
                val p = Point(pointSensor.x + i, lineofInterest)
                if (!listNoBeacons.contains(p) and !p.equals(nearestBeacon.point))
                    listNoBeacons.add(p)
            }
        }
    }

    /**
     * Returns a list of points near this sensor where a beacon can't exist, i.e. points
     * closer to it that than it's nearestBeacon.
     * Including sensor itself and nearest beacon.
     * Do it for all lines instead as it was for puzz1 version addNoBeaconPoints.
     * Hopefully no heap problems with full implementationon nobeacons as a set.
     * SLOW.
     * Need touse different strategy again. Do not generate listNobeacons at all.
     * Instead iterate through all sensors, their surrounding points
     * and see if that is ouside of any sensor's reach.
     * See new method BeaconExclusionZonePuzz2.availableBeaconPoints_surroundingpoints_manhattandistance_impl
     */
    fun addNoBeaconPointsPuzz2(listNoBeacons: MutableSet<Point>) {
        //println("Adding sensor $this")
        val manhattanDistance =
            this.pointSensor.manhattanDistance(this.pointBeacon)

        // Iterate through manhattanDistance x manhattanDistance X x Y square
        for (i in -manhattanDistance..manhattanDistance)
            for (j in -manhattanDistance..manhattanDistance) {
                val pointToAdd = Point(pointSensor.x + i, pointSensor.y + j)
                if (pointToAdd.manhattanDistance(pointSensor) <= manhattanDistance) {
                    listNoBeacons.add(pointToAdd)
                }
            }
    }

    /**
     * Returns points surrounding this sensor's noBeacons triangle.
     * These are then iterated through to find out one, which is not owned by any of the inside
     * sensor's nobeacon area within the limiting area of interest 4Mx4M.
     */
    fun surroundingPoints():List<Point>{

        val listSurroundingPoints = mutableListOf<Point>()

        for (i in -manhattanDistance -1..manhattanDistance + 1) {

            if(i == -manhattanDistance -1) { // Add only one point at left edge
                listSurroundingPoints.add(Point(pointSensor.x + i, pointSensor.y))
            } else  if( i <= 0) { // On left diamond
                listSurroundingPoints.add(Point(pointSensor.x + i, pointSensor.y + abs(manhattanDistance + i + 1)))
                listSurroundingPoints.add(Point(pointSensor.x + i, pointSensor.y - abs(manhattanDistance + i + 1)))
            } else if( i <= manhattanDistance){ // On right diamond
                listSurroundingPoints.add(Point(pointSensor.x + i, pointSensor.y + abs(manhattanDistance - i + 1)))
                listSurroundingPoints.add(Point(pointSensor.x + i, pointSensor.y - abs(manhattanDistance - i + 1)))
            }
            else {
                listSurroundingPoints.add(Point(pointSensor.x + i, pointSensor.y)) // Right edge
            }
        }
        return listSurroundingPoints
    }


    override fun toString(): String {
        return "Sensor($pointSensor), nearest $nearestBeacon"
    }
}