package fi.viware.AOC2022.day15beaconexclusionzone

class Sensor(val pointSensor: Point, val pointBeacon: Point) {

    var nearestBeacon: Beacon = Beacon(pointBeacon)



    override fun toString(): String {
        return "Sensor($pointSensor), nearest $nearestBeacon"
    }
}