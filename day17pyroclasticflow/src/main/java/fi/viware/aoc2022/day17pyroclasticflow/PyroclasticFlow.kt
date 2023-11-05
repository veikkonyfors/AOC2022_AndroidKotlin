package fi.viware.aoc2022.day17pyroclasticflow

const val CHAMBER_WIDTH = 6

/**
 * Class to drive pyroclastic rock flow in chamber
 */
class PyroclasticFlow() {

    val chamber = Chamber(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
    val rocks = Rocks()

    val rocksDropped = 0

    /**
     * Starts the flow of rocks
     */
    fun startFlow(){
        while(rocksDropped <= 2022) {
            chamber.drop(rocks.dropNext())  // Drop a rock to chamber
        }
    }


}