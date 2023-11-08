package fi.viware.aoc2022.day17pyroclasticflow

/**
 * Simulates jets of gas.
 */
class JetPattern(val jetPatternString:String) {

    var currentPush = 0

    /**
     * Gives the push of Jet Gas.
     * -1 to the left, +1 to the right
     *
     * 0 if an invalid character in jet pattern input.
     * A warning message will be printed in this case as well.
     */
    fun push(): Int{

        val ch = jetPatternString[currentPush++]
        if (currentPush >= jetPatternString.length) currentPush = 0

        when (ch){
            '<', '>' -> {}
            else -> println("JetPattern flaw in pattern: $jetPatternString")
        }

        return when (ch) {
            '>' -> 1
            '<' -> -1
            else -> push()
        }
    }
}