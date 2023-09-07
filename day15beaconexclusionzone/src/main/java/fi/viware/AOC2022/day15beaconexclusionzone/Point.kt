package fi.viware.AOC2022.day15beaconexclusionzone

import java.lang.Math.abs

data class Point(val x:Int, val y:Int) {

    /**
     * Returns manhattan distance between this point and the parameter one.
     */
    fun manhattanDistance(anotherPoint: Point): Int{
        return abs(this.x - anotherPoint.x) + abs(this.y - anotherPoint.y)
    }

    /* Changed to data class, no need for this any more

    override fun equals(other: Any?): Boolean {
        if ( (point.x == x) and (point.y == y)) return true
        return false
    }

     */

    override fun toString(): String {
        return "($x, $y)"
    }

}