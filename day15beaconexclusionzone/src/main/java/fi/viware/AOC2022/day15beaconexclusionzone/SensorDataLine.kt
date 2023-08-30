package fi.viware.AOC2022.day15beaconexclusionzone

class SensorDataLine(val line: String) {

    /**
     * From sensor data line, creates a sensor with nearest beacon.
     */
    fun toSensor():Sensor{
        val (sx, sy, bx, by) =
            line.replace("Sensor at x=", "")
                .replace(", y=",",")
                .replace(": closest beacon is at x=",",")
                .replace(", y=",",")
                .split(",")
        return Sensor(Point(sx.toInt(), sy.toInt()), Point(bx.toInt(), by.toInt()))
    }
}