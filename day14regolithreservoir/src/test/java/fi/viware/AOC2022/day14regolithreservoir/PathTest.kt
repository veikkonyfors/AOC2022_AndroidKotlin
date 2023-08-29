package fi.viware.AOC2022.day14regolithreservoir

import org.junit.Assert.*

import org.junit.Test

class PathTest {

    lateinit var pathLines: List<String>

    /*
@Before
fun setUp() {
    val file = File(
        ".",
        "input_test"
    )
    pathLines = file.readLines()
}
@Test
fun readPoints() {
    println(pathLines)
    assertEquals("[498,4 -> 498,6 -> 496,6, 503,4 -> 502,4 -> 502,9 -> 494,9]",pathLines.toString())

}


@Test
fun generatePaths() {
    var paths = mutableListOf<Path>()
    pathLines.forEach { l ->
        val pointStrings = l.split("->")
        var points = mutableListOf<Point>()

        pointStrings.forEach { p ->
            val (x, y) = p.split(",")
            points.add(Point(x.trim().toInt(), y.trim().toInt()))
        }

        paths.add(Path(points))
    }
    println(paths)
    assertEquals("[[498, 4 ->, 498, 6 ->, 496, 6 ], [503, 4 ->, 502, 4 ->, 502, 9 ->, 494, 9 ]]",
        paths.toString())
}*/

    @Test
    fun getAllPoints() {
        var path = Path(listOf(Point(498, 6), Point(496, 6)))
        println(path.getAllPoints())
        assertEquals("[498, 6 ->, 497, 6 ->, 496, 6 ->]",path.getAllPoints().toString())
        path = Path(listOf(Point(498, 4), Point(498, 6), Point(496, 6)))
        println(path.getAllPoints())
        assertEquals("[498, 4 ->, 498, 5 ->, 498, 6 ->, 497, 6 ->, 496, 6 ->]", path.getAllPoints().toString())
        path = Path(listOf(Point(503, 4), Point(502, 4), Point(502, 9), Point(494, 9)))
        println(path.getAllPoints())
        assertEquals("[503, 4 ->, 502, 4 ->, 502, 5 ->, 502, 6 ->, 502, 7 ->, 502, 8 ->, 502, 9 ->, 495, 9 ->, 496, 9 ->, 497, 9 ->, 498, 9 ->, 499, 9 ->, 500, 9 ->, 501, 9 ->, 494, 9 ->]", path.getAllPoints().toString())
    }
}