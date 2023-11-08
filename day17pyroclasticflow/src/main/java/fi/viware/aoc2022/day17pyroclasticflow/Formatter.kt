package fi.viware.aoc2022.day17pyroclasticflow

class Formatter(){

    fun shapeToString(shape: MutableList<List<Int>>) : String {
        // Determine the dimensions of the 2D array
        val numCols = shape.size
        val maxNumRows = shape.maxByOrNull { it.size }?.size ?: 0

// Create 2D array for the shape of rock and fill it with the values
        val shape2DRay = Array(maxNumRows) { row ->
            IntArray(numCols) { col ->
                shape.getOrNull(col)?.getOrNull(row) ?: -1
            }
        }
        var s=""
        for (col in shape2DRay) {
            for (row in col) {
                s += "${row}, "
            }
            s += "\n" // Start a new line for the next row
        }
        return s
    }

    fun shapeToStringUsingPileHeigth(shape: MutableList<List<Int>>) : String {
        var s = ""
        val highestPile = shape.maxByOrNull { it.maxOrNull() ?: 0 }?.maxOrNull() ?: 0
        for (h in 0..highestPile) {
            s += "|"
            shape.forEach {pile ->
                if (pile == emptyList<Int>()) s += " "
                else if(pile.contains(h)) s += "#"
                else s += " "
            }
            s += "|\n"
        }
        return s
    }

}