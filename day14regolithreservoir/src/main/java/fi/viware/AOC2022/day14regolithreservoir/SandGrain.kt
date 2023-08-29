package fi.viware.AOC2022.day14regolithreservoir

class SandGrain(var point: Point) {

    fun down():Point{
        return Point(point.x,point.y + 1)
    }

    fun goDown() {
        point.y += 1
    }

    fun downLeft():Point{
        return Point(point.x -1,point.y + 1)
    }

    fun goDownLeft() {
        point.y += 1
        point.x -= 1
    }

    fun downRight():Point{
        return Point(point.x +1,point.y + 1)
    }

    fun goDownRight() {
        point.y += 1
        point.x += 1
    }

    override fun toString(): String {
        return point.toString()
    }

}