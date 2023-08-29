package fi.viware.AOC2022.day14regolithreservoir

import android.util.Log

private const val TAG = "fi.viware.AOC2022.day14regolithreservoir.Path"

/**
 * Deals with the path in various ways
 */
class Path(val points: List<Point>) {

    /**
     * Returns a list of all Points this path covers.
     */
    fun getAllPoints(): List<Point>{
        var allPoints = mutableListOf<Point>()

        this.points.forEachIndexed { i, p -> // Loop through all the defining points
            allPoints.add(p) // Add this defining point
            if(i >= points.size - 1) return@forEachIndexed // Break out after last defining point
            // We now should add all the points between this defining point and the next defining point
            when {
                p.x == points[i+1].x -> { // Line is vertical
                    if (p.y < points[i + 1].y) // This is downwards i.e. y increases
                    // 498,4 -> 498,6
                        for (i in p.y + 1..<points[i + 1].y) { // Do not add the latter defining point, it will be added at start of next round
                            allPoints.add(Point(p.x, i))
                        } else if (p.y > points[i + 1].y) // It is upwards i.e. y decreases
                    // 498,6 -> 498,4
                        for (i in points[i + 1].y + 1 .. (p.y) - 1) { // Do not add the latter defining point, it will be added at start of next round
                            allPoints.add(Point(p.x, i))
                        } else // y is the same
                        Log.e("${TAG}.getAllPoints", "Duffy duplicate defining points for path $points")
                }

                p.y == points[i + 1].y -> { // This line is horizontal
                    if(p.x < points[i+1].x) // This is rightwards, x increases
                    // 496,6 -> 498,6
                        for (i in p.x + 1..<points[i + 1].x) { // Do not add the latter defining point, it will be added at start of next round
                            allPoints.add(Point(i, p.y))
                        } else if(p.x > points[i+1].x)
                    // 498,6 -> 496,6
                        for (i in points[i + 1].x +1..<p.x) { // Do not add the latter defining point, it will be added at start of next round
                            allPoints.add(Point(i, p.y))
                        } else // y is the same
                        Log.e("${TAG}.getAllPoints", "Duffy duplicate defining points for path $points")
                }
                else -> { // Flaw in defining points, they are not consecutive
                    Log.e("${TAG}.getAllPoints", "Duffy, non-consecutive defining points for path $points")
                }
            }
        }
        return allPoints
    }
    override fun toString(): String {
        return points.toString().replace("->]","]") // Rewmove arrowfrom final point
    }

}

