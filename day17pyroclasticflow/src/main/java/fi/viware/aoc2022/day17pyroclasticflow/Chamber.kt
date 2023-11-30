package fi.viware.aoc2022.day17pyroclasticflow

const val VERTICAL_MARGIN = 2
const val LEFT_MARGIN = 2

class Chamber(val jetPatternString:String) {

    private val columnPiles = listOf(mutableListOf(0), mutableListOf(0), mutableListOf(0), mutableListOf(0), mutableListOf(0), mutableListOf(0), mutableListOf(0))

    val jetPattern = JetPattern(jetPatternString)

    var highestPile = 0

    var round = 0

    /**
     * Drops a rock to chamber.
     * Gas jets move it horizontally one slot and after it drops down one slot.
     * This loop is repeated until rock hits either another already dropped rock in the chamber or it's base.
     */
    fun drop(droppingRock: Rock){
        round += 1
        var collide = false
        var pushPattern = ""

        //println("Dropped droppingRock:\n$droppingRock\n") // 4. round ><>>><< ><>>><<

        while (! collide) {
            val d = jetPattern.push()
            pushPattern += when(d) { -1 -> '<'; 1-> '>'; else -> 'x'}
            // Make a copy and move it to be used in collision test
            var movedRock=droppingRock.copy()
            movedRock.moveHorizontally(d)
            if (!collide(movedRock)) droppingRock.moveHorizontally(d) // Move dropping rock
            movedRock=droppingRock.copy()
            movedRock.down()
            when (collide(movedRock)) {
                true -> collide = true
                false -> droppingRock.down()
            }
            //if (round == 16) println("Afterwhile droppingRock:\n$droppingRock, pushPattern $pushPattern") //\n, chamber:\n$this")
        }
        val netPush = pushPattern.count{ it == '>' } - pushPattern.count{ it == '<' }
        //println("adding droppingRock  with pushPattern: ${pushPattern} }")
        add(droppingRock)
    }

    /**
     * Checks whether droppingRock collides with chamber.
     */
    fun collide(movedRock: Rock):Boolean{

        // Go through each slot in droppingRock and check if a slot collides with any of the slots
        // In champer's corresponding columnPile
        movedRock.shape.forEachIndexed { rockPileIndex, rockSlotList ->
            rockSlotList.forEach { rockSlot ->
                columnPiles[rockPileIndex].forEach {chamberSlot ->
                    //println("rockPileIndex, chamberSlot, rockSlot, movedRock.height: $rockPileIndex, $chamberSlot, $rockSlot, ${movedRock.height}")
                    // chamber slot reaching rockslot + height of rock + highest pile on the chamber.
                    // Rock is always dropped 4 slots higher than the highest pile in chamber.
                    if (chamberSlot == rockSlot + movedRock.height + highestPile) return true
                }
            }
        }
        return false
    }

    /**
     * Adds a rock to chamber.
     */
    fun add(rock: Rock) {
        rock.shape.forEachIndexed { rockPileIndex, rockSlotList ->
            rockSlotList.forEachIndexed { i, s ->
                columnPiles[rockPileIndex].add(s + rock.height + highestPile)
            }
        }
        //highestPile += rock.highestPile()
        highestPile = columnPiles.maxByOrNull { it.maxOrNull() ?: 0 }?.maxOrNull() ?: 0
    }

    override fun toString(): String {
        return Formatter().shapeToStringUsingPileHeigth(columnPiles.toMutableList())
    }
}