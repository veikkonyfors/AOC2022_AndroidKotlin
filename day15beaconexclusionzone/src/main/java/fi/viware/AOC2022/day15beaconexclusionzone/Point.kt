package fi.viware.AOC2022.day15beaconexclusionzone

class Point(val x:Int, val y:Int) {

    /**
     * Returns manhattan distance between this point and the parameter one.
     */
    fun manhattanDistance(anotherPoint: Point): Int{
        return 0
    }
    override fun toString(): String {
        return "($x, $y)"
    }

}