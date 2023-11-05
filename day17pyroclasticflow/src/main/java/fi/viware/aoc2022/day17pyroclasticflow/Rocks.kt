package fi.viware.aoc2022.day17pyroclasticflow



/**
 * Simulates different kind of rocks dropped in sequence.
 */
class Rocks {

    var currentShape = -1
    /**
     * Drops down next rock
     */
    fun dropNext(): Rock{
        currentShape += 1
        return when(currentShape % 5) {
            0 -> Rock(mutableListOf(emptyList(), listOf(0), listOf(0), listOf(0), listOf(0), emptyList(), emptyList()))
            1 -> Rock(mutableListOf(emptyList(),emptyList(),listOf(1),listOf(0, 1, 2),listOf(1),emptyList(),emptyList()))
            2 -> Rock(mutableListOf(emptyList(),emptyList(),listOf(0),listOf(0),listOf(0, 1, 2),emptyList(),emptyList()))
            3 -> Rock(mutableListOf(emptyList(),emptyList(),listOf(0,1,2,3),emptyList(),emptyList(),emptyList(),emptyList()))
            4 -> Rock(mutableListOf(emptyList(), emptyList(), listOf(0,1), listOf(0,1), emptyList(), emptyList(), emptyList()))
            else -> Rock(mutableListOf())
        }
    }
}