package fi.viware.AOC2022.day15beaconexclusionzone

class Sensor(val pointSensor: Point, val pointBeacon: Point) {

    var nearestBeacon: Beacon = Beacon(pointBeacon)


    /**
     * Returns a list of points near this sensor where a beacon can't exist, i.e. points
     * closer to it that than it's nearestBeacon.
     * Including sensor itself and nearest beacon.
     */
    fun noBeaconPoints(sensor: Sensor): List<Point> {
        var noBeaconPoints = mutableListOf<Point>(sensor.pointSensor, sensor.pointBeacon)
        val manhattanDistance =
            sensor.pointSensor.manhattanDistance(sensor.pointBeacon)
        for (i in 1..manhattanDistance) {
            noBeaconPoints.add(Point(i, manhattanDistance - i))
        }
        return noBeaconPoints
    }

    override fun toString(): String {
        return "Sensor($pointSensor), nearest $nearestBeacon"
    }
}