package fi.viware.day14regolithreservoir

import kotlin.properties.Delegates

/**
 * Cave gets path lines as it's parameter.
 * It then generates list of matching Path objects containing all the associated Points as a list.
 * Then SandGrains are dropped one after each other, until a leak takes place.
 * Don't know if a stuck condition might happen.
 */
class Cave(val pathLines:List<String>) {

    val rockPoints = mutableListOf<Point>()
    val sandPoints = mutableListOf<Point>()
    val occupiedPoints = mutableListOf<Point>()
    var leaking = false
    var paths = mutableListOf<Path>()
    var minX by Delegates.notNull<Int>()
    var maxX by Delegates.notNull<Int>()
    var minY by Delegates.notNull<Int>()
    var maxY by Delegates.notNull<Int>()

    init {
        generatePaths()
        generateRockPoints()
        occupiedPoints.addAll(rockPoints)
    }

    /**
     * Generates list of paths from pathLines
     */
    fun generatePaths(){
        pathLines.forEach { l ->
            val pointStrings = l.split("->")
            var points = mutableListOf<Point>()

            pointStrings.forEach { p ->
                val (x, y) = p.split(",")
                points.add(Point(x.trim().toInt(), y.trim().toInt()))
            }
            paths.add(Path(points))
        }
    }

    /**
     * Generates a list of all points along all paths into rockPoints list of points.
     */
    fun generateRockPoints(){
        paths.forEach {
            it.getAllPoints().forEach {
                rockPoints.add(it)
            }
        }
        findMinMaxX()
        findMinMaxY()
    }

    /**
     * Finds minimum and maximum x on rockPoints
     */
    fun findMinMaxX(){
        minX = rockPoints[0].x
        maxX = rockPoints[0].x
        rockPoints.forEach {
            if (it.x < minX) minX = it.x
            if (it.x > maxX) maxX = it.x
        }
    }

    /**
     * Finds minimum and maximum x on rockPoints
     */
    fun findMinMaxY(){
        minY = rockPoints[0].y
        maxY = rockPoints[0].y
        rockPoints.forEach {
            if (it.y < minY) minY = it.y
            if (it.y > maxY) maxY = it.y
        }
    }


    fun fill() {
        while (!leaking) {
            leaking = drop(SandGrain(Point(500,0)))
        }
    }

    fun drop(sandGrain: SandGrain): Boolean{
        var stuck = false
        while (!(leaking or stuck)) {
            if ((sandGrain.point.x < minX) or (sandGrain.point.x > maxX)) {
                leaking = true; return true
            }

            if (sandGrain.point.y > maxY) {
                leaking = true; return true
            }

            if( isFree(sandGrain.down())){
                sandGrain.goDown()
            } else if(isFree(sandGrain.downLeft())) {
                sandGrain.goDownLeft()
            } else if (isFree(sandGrain.downRight())){
                    sandGrain.goDownRight()
            } else {
                stuck = true
                occupiedPoints.add(sandGrain.point)
                sandPoints.add(sandGrain.point)
                println("Add SandPoint ${sandGrain.toString()}, sandPoints.size: ${sandPoints.size}")
            }
        }
        return false
    }

    fun isFree(point: Point): Boolean {
        occupiedPoints.forEach {p ->
            if (p.equals(point)) return false
        }
        return true
    }
}