package fi.viware.aoc2022.day17pyroclasticflow

/**
 * Simulates a single rock.
 * Instantiated with a list of ints, defining shape of the rock.
 * Shape is stored in seven lists of ints, one for each column of the chamber.
 * 0 on a list tells rock occupies lowest position on associated column,
 * 3 in turn tels rock is taking up the fourth vertical position of the column in question.
 * An empty list tells rock doesn't occupy this slot.
 * I.e. [[],[],[1],[0,1,2],[1],[],[]] defines a rock like below:
 *   .#.
 *   ###
 *   .#.
 * with two empty columns on the left and two on the right.
 *
 * @param height How much lowest slot is initially above highest slot of the chamber.
 * Decreased on each call by one. Running negative means rock and chamber have slots at the same height
 * and it's possible for them to collide.
 */
data class Rock(var shape: MutableList<List<Int>>, var height: Int = 4) {

    /**
     * Moves rock one slot left or right if possible.
     * leftOrRight: -1 to left, +1 to right.
     */
    fun moveHorizontally(leftOrRight:Int){

        when(leftOrRight){
            -1 -> left()
            else -> right()
        }

    }

    /**
     * Moves rock down one slot on the chamber.
     * Does so by decreasing height property.
     */
    fun down(){
        height -= 1
    }

    /**
     * Moves rock one slot left if rock not already on left wall
     */
    private fun left(){
        if(shape[0] == emptyList<Int>()) {
            shape = shape.drop(1).toMutableList()
            shape.add(CHAMBER_WIDTH, emptyList())
        }
    }

    /**
     * Moves rock one slot right if rock not already on left wall
     */
    private fun right(){
        if(shape[CHAMBER_WIDTH] == emptyList<Int>()) {
            shape = shape.dropLast(1).toMutableList()
            shape.add(0, emptyList())
        }
    }

    fun highestPile(): Int{
        return shape.maxByOrNull { it.size }?.size ?: 0
    }

    override fun toString(): String {
        return Formatter().shapeToStringUsingPileHeigth(shape)
    }


}