package fi.viware.AOC2022.day14regolithreservoir

class Point(var x: Int, var y: Int) {

    fun equals(point: Point): Boolean{
        if ( (point.x == x) and (point.y == y)) return true
        return false
    }
    override fun toString(): String {
        return "$x, $y ->"
    }
}