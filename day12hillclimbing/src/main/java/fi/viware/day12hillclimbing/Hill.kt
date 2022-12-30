package fi.viware.day12hillclimbing

import java.util.*

class Hill constructor(val hillHeigths:List<String> ) {


    var startRow: Int = 0
    var startCol: Int = 0
    var endRow: Int = 0
    var endCol: Int = 0
    var cost: Array<Array<Int>> = Array(hillHeigths.size) { Array(hillHeigths[0].length) { -1 } }
    var carryOn: Boolean = true
    var updated:Array<Array<Boolean>> = Array(hillHeigths.size) { Array(hillHeigths[0].length) { false } }
    var pathCost:Int=0 // Cost from E to S
    var aCost:MutableList<Int> = mutableListOf() // List to hold costs for each a found

    init {
        findStartAndEnd()
        println("S: "+startRow.toString()+","+startCol.toString()+", E: "+endRow.toString()+","+endCol.toString())
        println(hillHeigths.size.toString()+"x"+hillHeigths[0].length)
        cost[endRow][endCol] = 0
    }

    /**
     * iterate solves the first puzzle.
     * I.e. finds the shortest path from E to S
     */
    fun iterate() {
        carryOn=false
        hillHeigths.forEachIndexed { i, row ->
            row.forEachIndexed() { j, col ->
                if ( ! updated[i][j] and (cost[i][j] != -1)) { // Either E or a cost updated on previous iteration -> update surrounding costs
                    if (col == 'E') { // Just a check
                        println("Found E")
                    }
                    if (col == 'S') {
                        println("Found S")
                        println(cost[i][j])
                        pathCost=cost[i][j] // Eventually when we reach S for the first time, it's the shortest path
                    }
                    // Check up, right, down and left slots. If they are down one at most, update cost to be this slot + 1.
                    update_costs(i, j)
                }
            }
        }
    }

    fun solve2ndPuzzle():Int {

        hillHeigths.forEachIndexed { i, row ->
            row.forEachIndexed() { j, col ->
                if(col=='a' && cost[i][j]!=-1) {
                    aCost.add(cost[i][j])
                }
            }
        }

        aCost.sort()
        return aCost[0]
    }

    fun update_costs(_row:Int, _col:Int){
        update_left(_row, _col)
        update_up(_row, _col)
        update_right(_row, _col)
        update_down(_row, _col)
        updated[_row][_col]=true
    }

    fun update_left(_row:Int, _col:Int){
        if(_col>0){ // If there is a row left,
            if(! updated[_row][_col-1] and (heigth(_row, _col) <= heigth(_row, _col-1)+1)) { // down one at most
                cost[_row][_col-1] = cost[_row][_col]+1 // its cost will be cost of this slot +1
                carryOn=true
            }
        }
    }

    fun update_up(_row:Int, _col:Int){
        if(_row>0){ // If there is a col up, its cost will be cost of this slot +1
            if(! updated[_row-1][_col] and (heigth(_row, _col) <= heigth(_row-1, _col)+1)) { // down one at most
                cost[_row-1][_col] = cost[_row][_col]+1
                carryOn=true
            }
        }
    }

    fun update_right(_row:Int, _col:Int){
        if(_col<hillHeigths[_row].length-1){ // If there is a col right, its cost will be cost of this slot +1
            if(! updated[_row][_col+1] and (heigth(_row, _col) <= heigth(_row, _col+1)+1)) { // down one at most
                cost[_row][_col+1] = cost[_row][_col]+1
                carryOn=true
            }
        }
    }

    fun update_down(_row:Int, _col:Int){
        if(_row<hillHeigths.size-1){ // If there is a col right, its cost will be cost of this slot +1
            if(! updated[_row+1][_col] and (heigth(_row, _col) <= heigth(_row+1, _col)+1)) { // down one at most
                cost[_row+1][_col] = cost[_row][_col]+1
                carryOn=true
            }
        }
    }

    fun heigth(_row:Int, _col:Int):Int {
        if(hillHeigths[_row][_col] == 'S') return 'a'.code
        if(hillHeigths[_row][_col] == 'E') return 'z'.code
        return hillHeigths[_row][_col].code
    }

    fun findStartAndEnd() {
        hillHeigths.forEachIndexed { i, row ->
            row.forEachIndexed { j, slot ->
                if (slot == 'S') {
                    startRow = i
                    startCol = j
                }
                if (slot == 'E') {
                    endRow = i
                    endCol = j
                }
            }
        }
    }

    override fun toString(): String {
        return Arrays.deepToString(cost)
    }
}