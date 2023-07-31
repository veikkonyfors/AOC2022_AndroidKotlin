package fi.viware.AOC2022.day13distresssignal.puzz2

/**
 * Package for puzz2, with a fresh touch.
 * Signals no more generated with listof method as in first puzzle.
 */

/**
 * Manage signal in various ways.
 *
 * Initially given signalString is transformed to matching list of integers and/or sublists.
 *
 * signalString needs to start with [ and end with ]
 *
 */
class Signal(val stringSignal: String) {
    var listSignal: List<Any> = listOf()

    init {
        listSignal = parseStringSignal2ListSignal(stringSignal)
    }



    /*
        /**
     * Parse given signal in String format into a list of integers and lists.
     * Do it recursively. Once a [ bracket is met, locate matching ] bracket and recursively parse
     * that string into a list. This list is then added to the higher level list.
     */
    Just for note:
    can't do it with foreachindexed, as it will carry on character by character and does not skip
    the subsignals extracted already.
    E.g. with [3,[4,5],5], parsing will continue at the 4 after subsignal [4,5] has been processed.
    Trying to implement this with indexed for loop, again!
    fun parseStringSignal2ListSignalForEachIndexed(stringSignal: String): List<Any> {
        if (stringSignal.startsWith("[") && stringSignal.endsWith("]")) {
            val trimmedStringSignal = stringSignal.drop(1).dropLast(1).replace(" ", "")
            val listSignal = mutableListOf<Any>()
            //var subListSignal

            trimmedStringSignal.forEachIndexed forEach@{ index, char ->
                when {
                    char.isDigit() ->
                        listSignal.add(char.digitToInt())
                    char == ',' ->
                        return@forEach
                    char == '[' -> {
                        val subStringSignal =
                            extractSubSignalString(trimmedStringSignal.drop(index))
                        val subListSignal = parseStringSignal2ListSignal(subStringSignal)
                        listSignal.add(subListSignal)
                        index += subStringSignal.length
                    }
                    char == ']' ->
                        return listSignal
                }
            }
            return listSignal
        }
        return emptyList()
    }
     */

    /**
     * Parse given signal in String format into a list of integers and lists.
     * Do it recursively. Once a [ bracket is met, locate matching ] bracket and recursively parse
     * that string into a list. This list is then added to the higher level list.
     */
    fun parseStringSignal2ListSignal(stringSignal: String): List<Any> {
        if (stringSignal.startsWith("[") && stringSignal.endsWith("]")) {
            val trimmedStringSignal = stringSignal.drop(1).dropLast(1).replace(" ", "")
            val listSignal = mutableListOf<Any>()

            // Need to use explisit index i, as subsignals need to be skipped on higher level loop.
            var i = -1
            for (index in trimmedStringSignal.indices) {
                i++
                if (i >= trimmedStringSignal.length) break
                val c = trimmedStringSignal[i]
                when {
                    c.isDigit() ->
                        listSignal.add(c.digitToInt())
                    c == ',' ->
                        continue
                    c == '[' -> {
                        val subStringSignal =
                            extractSubSignalString(trimmedStringSignal.drop(index))
                        val subListSignal = parseStringSignal2ListSignal(subStringSignal)
                        listSignal.add(subListSignal)
                        i += subStringSignal.length // Skip subsignal characters
                    }
                    c == ']' ->
                        return listSignal
                }
            }
            return listSignal
        }
        return emptyList()
    }


    /**
     * Truncates stringSignal starting with [ to to only contain items up to the matching ].
     * Extra items exist in the end as on previous level stringSignal has been
     * parsed from start up to sublist indicator [, and there may be extra items after the matching
     * end list indicator ], belonging to previous level's list.
     * Sample: [1,[2,3]],7,8,9] will be truncated to [1,[2,3]]
     */
    fun extractSubSignalString(extraneousStringSignal: String): String {
        var numLeftBrackets = 0
        var numRightBrackets = 0
        var subString = ""

        /*
        Just to make a note:
        return@scan doesn't break foreachindexed loop, it only skips the current item
        and carries with the next one, like continue does in for loops.
        chatGPT says breaking functionality is not available in foreachindexed and one has to use
        traditional for loop instead.

        extraneousStringSignal.forEachIndexed scan@{ index, c ->
            if (c == '[') numLeftBrackets += 1
            if (c == ']') {
                if (numRightBrackets == numLeftBrackets - 1) { // If this is a match
                    subString = extraneousStringSignal.substring(0, index + 1)
                    return@scan
                    // This was working, but preferred doing this otherwise.
                    // return subString
                }
            }
        }
        return subString
        */

        for (index in extraneousStringSignal.indices) {
            val c = extraneousStringSignal[index]
            when {
                c == '[' -> numLeftBrackets += 1
                c == ']' -> {
                    numRightBrackets += 1
                    if (numRightBrackets == numLeftBrackets) { // If this is a match
                        subString = extraneousStringSignal.substring(0, index + 1)
                        break
                    }
                }
            }
        }
        return subString
    }

    /**
     * Compares this signal to the given other one.
     * If this signal is lower, returns true, otherwise false.
     */
    fun isLower(signal2:Signal):Boolean{

        listSignal.forEachIndexed { index, any ->
            // If given signal runs out of elements, this signal is higher
            if (signal2.listSignal.size <= index+1) return false

            // If both elements are ints and this signal's one is smaller, this signal is lower
            if (listSignal[index] is Int && signal2.listSignal[index] is Int) {
                val i: Int = listSignal[index] as Int
                val j: Int = signal2.listSignal[index] as Int
                return i < j //listSignal[index] < signal2.listSignal[index]
            }
        }

        return true
    }

    override fun toString(): String {
        return listSignal.toString()
    }
}