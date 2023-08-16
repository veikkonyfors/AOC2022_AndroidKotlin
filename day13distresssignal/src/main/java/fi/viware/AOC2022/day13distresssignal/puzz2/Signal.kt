package fi.viware.AOC2022.day13distresssignal.puzz2

/**
 * Package for puzz2, with a fresh touch.
 * Signals no more generated with listof method as in first puzzle.
 */

/**
 * Manage signal in various ways.
 *
 * Initially given signalString is transformed to matching list of integers and/or sublists of Signals.
 *
 * signalString needs to start with [ and end with ]
 *
 */

private const val TAG = "fi.viware.AOC2022.day13distresssignal.puzz2.Signal"
class Signal(var stringSignal: String) {
    var listSignal: List<Any> = listOf()

    init {
        listSignal = parseStringSignal2ListSignal(stringSignal)
    }

    // Secondary constructor for instantiating out of a list
    constructor(list: List<Any>) : this(list.toString()) {
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

        val listSignal = mutableListOf<Any>()

        // stringSignal needs always start with [ and end with ]. Skip those first.
        if (stringSignal.startsWith("[") && stringSignal.endsWith("]")) {
            val trimmedStringSignal = stringSignal.drop(1).dropLast(1).replace(" ", "")

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
                            extractSubSignalString(trimmedStringSignal.drop(i))
                        val subListSignal = parseStringSignal2ListSignal(subStringSignal)
                        listSignal.add(subListSignal)
                        i += subStringSignal.length-1 // Skip subsignal characters
                    }
                    c == ']' -> {
                        // We must never come here, as leading and terminating brackets are removed to start with
                        println("$TAG, Mismatching bracketing :$stringSignal")
                        listSignal.add("$TAG, Mismatching bracketing :$stringSignal")
                        break
                    }

                }
            }
        } else
        {
            // Unittest would require Log to be mocked. Use println for the time being.
            //Log.d(TAG, "Missing surrounding [ and/or ] :$stringSignal")
            println("$TAG, Missing surrounding [ and/or ] :$stringSignal")
            listSignal.add("$TAG, Missing surrounding [ and/or ] :$stringSignal")
        }
        return listSignal
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
     * If signals equal, returns true, otherwise false.
     */
    fun isEqual(signal2:Signal):Boolean {

        listSignal.forEachIndexed { index, any ->

            // If parameter signal runs out of elements, this signal is not lower
            if (signal2.listSignal.size <= index) return false

            // If exactly one value is an integer, convert integer to a list which contains
            // that integer as its only value, then retry the comparison.
            // This needs to be checked first, as otherwise signal size will be considered zero
            // and test for signal2's list running out will fail

            // If this index item is a list and signal2 parameter index item is Int: Turn latter into a list and then
            // compare
            if (listSignal[index] is MutableList<*> && signal2.listSignal[index] is Int) {
                val thisIndexedSubsignal = Signal(listSignal[index].toString())
                val signal2IndexedIntSubsignal = Signal("[${signal2.listSignal[index]}]")
                if (thisIndexedSubsignal.isEqual(signal2IndexedIntSubsignal)) return@forEachIndexed
                return false
            }

            // If this is an Int and signal2 parameter is List: Turn former into a list and then
            // compare
            /*if (listSignal[index] is Int && signal2.listSignal[index] is MutableList<*>) {
                val signalThisAsList: MutableList<Any> =
                    mutableListOf(Signal(this.listSignal.toString()))
                val signalThisAsListList = Signal(signalThisAsList.toString())
                if (signalThisAsListList.isEqual(signal2)) return@forEachIndexed
                return false*/

                if (listSignal[index] is Int && signal2.listSignal[index] is MutableList<*>) {
                    val thisIndexedSubsignal = Signal("[${listSignal[index]}]")
                    val signal2IndexedIntSubsignal = Signal(signal2.listSignal[index].toString())
                    if(thisIndexedSubsignal.isEqual(signal2IndexedIntSubsignal)) return@forEachIndexed
                    return false
            }

            // If both elements are ints and this signal's one is smaller, this signal is lower
            if (listSignal[index] is Int && signal2.listSignal[index] is Int) {
                val i: Int = listSignal[index] as Int
                val j: Int = signal2.listSignal[index] as Int
                if (i == j) return@forEachIndexed
                return false
            }

            // If both signals have a list element here, compare those as individual Signals
            if (listSignal[index] is MutableList<*> && signal2.listSignal[index] is MutableList<*>) {
                // Make a signal of both elements, and compare those
                val subsignal1 = Signal(listSignal[index].toString())
                val subSignal2 = Signal(signal2.listSignal[index].toString())
                if(subsignal1.isEqual(subSignal2)) return@forEachIndexed
                return false

            }
        }
        return true
    }

    /**
     * Compares this signal to another one given as parameter.
     * If this signal is lower, returns true, otherwise false.
     * Spec didn't actually tell clearly how should equal signals be treated
     * i.e. is an equal pair in right order or not.
     * puzzle just happened to do it in right way.
     * For the second puzzle it doesn't matter either, as equal signals will be adjacent
     * in sorted order anyway.
     */
    fun isLower(signal2:Signal):Boolean{

        listSignal.forEachIndexed { index, any ->

            // If parameter signal runs out of elements, this signal is not lower
            if (signal2.listSignal.size <= index) return false

            // If exactly one value is an integer, convert integer to a list which contains
            // that integer as its only value, then retry the comparison.
            // This needs to be checked first, as otherwise signal size will be considered zero
            // and test for signal2's list running out will fail

            // If this index item is a list and signal2 parameter index item is Int: Turn latter into a list and then
            // compare
            if (listSignal[index] is MutableList<*> && signal2.listSignal[index] is Int) {
                val thisIndexedSubsignal = Signal(listSignal[index].toString())
                val signal2IndexedIntSubsignal = Signal("[${signal2.listSignal[index]}]")
                if(thisIndexedSubsignal.isEqual(signal2IndexedIntSubsignal)) return@forEachIndexed
                return thisIndexedSubsignal.isLower(signal2IndexedIntSubsignal)
            }

            // If this is an Int and signal2 parameter is List: Turn former into a list and then
            // compare
            if (listSignal[index] is Int && signal2.listSignal[index] is MutableList<*>) {
                val thisIndexedSubsignal = Signal("[${listSignal[index]}]")
                val signal2IndexedIntSubsignal = Signal(signal2.listSignal[index].toString())
                if(thisIndexedSubsignal.isEqual(signal2IndexedIntSubsignal)) return@forEachIndexed
                return thisIndexedSubsignal.isLower(signal2IndexedIntSubsignal)
            }

            // If both elements are ints and this signal's one is smaller, this signal is lower
            if (listSignal[index] is Int && signal2.listSignal[index] is Int) {
                val i: Int = listSignal[index] as Int
                val j: Int = signal2.listSignal[index] as Int
                if (i == j) return@forEachIndexed
                return i < j
            }

            // If both signals have a list element here, compare those as individual Signals
            if (listSignal[index] is MutableList<*> && signal2.listSignal[index] is MutableList<*>) {
                // Make a signal of both elements, and compare those
                val subsignal1 = Signal(listSignal[index].toString())
                val subSignal2 = Signal(signal2.listSignal[index].toString())
                if(subsignal1.isEqual(subSignal2)) return@forEachIndexed
                return subsignal1.isLower(subSignal2)

            }
        }
        return true
    }

    fun getAsListStrings(): List<String> {
        val listToReturn = mutableListOf<String>()
        listSignal.forEach {
            listToReturn.add(it.toString())
        }
        return listToReturn
    }

    override fun toString(): String {
        return listSignal.toString()
    }
}