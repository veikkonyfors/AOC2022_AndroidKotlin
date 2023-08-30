package fi.viware.AOC2022.day15beaconexclusionzone

import java.lang.Math.abs

class Point(val x:Int, val y:Int) {

    /**
     * Returns manhattan distance between this point and the parameter one.
     */
    fun manhattanDistance(anotherPoint: Point): Int{
        return abs(this.x - anotherPoint.x) + abs(this.y - anotherPoint.y) -1
    }

    fun equals(point: Point): Boolean{
        if ( (point.x == x) and (point.y == y)) return true
        return false
    }

    override fun toString(): String {
        return "($x, $y)"
    }

}