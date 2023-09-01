package fi.viware.AOC2022.day15beaconexclusionzone

class Sensor(val pointSensor: Point, val pointBeacon: Point) {

    var nearestBeacon: Beacon = Beacon(pointBeacon)


    /**
     * Returns a list of points near this sensor where a beacon can't exist, i.e. points
     * closer to it that than it's nearestBeacon.
     * Including sensor itself and nearest beacon.
     */
    fun noBeaconPoints(): List<Point> {
        var noBeaconPoints = mutableListOf<Point>()
        val manhattanDistance =
            this.pointSensor.manhattanDistance(this.pointBeacon)
        for (i in -manhattanDistance..manhattanDistance) {
            for (j in -manhattanDistance..manhattanDistance) {
                if (pointSensor.manhattanDistance(Point(pointSensor.x + i, pointSensor.y + j)) <= manhattanDistance) {
                    noBeaconPoints.add(Point(pointSensor.x + i, pointSensor.y + j))
                }
            }
        }
        return noBeaconPoints
    }

    override fun toString(): String {
        return "Sensor($pointSensor), nearest $nearestBeacon"
    }
}