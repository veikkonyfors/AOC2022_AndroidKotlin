package fi.viware.AOC2022.day14regolithreservoir

/**
 * Derived class from Cave of puzz1.
 * Has relevant methods overwritten to fulfill puzz2 rules.
 */
class CavePuzz2(pathLines: List<String>) : Cave(pathLines) {

    /**
     * Fills the gave with sandgrains using rules of puzzle2.
     */
    override fun fill() {
        while (!atRest) {
            atRest = drop(SandGrain(Point(500,0)))
        }
    }

    /**
     * Drops one sandgrain per rules of puzzle2
     */
    override fun drop(sandGrain: SandGrain): Boolean{
        var stuck = false // Nowhere to go any more
        while (! stuck) {
            if( isFree(sandGrain.down())){
                sandGrain.goDown()
            } else if(isFree(sandGrain.downLeft())) {
                sandGrain.goDownLeft()
            } else if (isFree(sandGrain.downRight())){
                sandGrain.goDownRight()
            } else {
                stuck = true
                if(!sandGrain.point.equals(Point(500,0))){
                    occupiedPoints.add(sandGrain.point)
                    sandPoints.add(sandGrain.point)
                    println("Add SandPoint ${sandGrain.toString()}, sandPoints.size: ${sandPoints.size}")
                } else return true // We are at rest now
            }
        }
        return false
    }

    /**
     * Checks if point is not occupied per rules of puzzle2
     */
    override fun isFree(point: Point): Boolean {
        if(point.y >= maxY + 2) return false
        occupiedPoints.forEach {p ->
            if (p.equals(point)) return false
        }
        return true
    }
}