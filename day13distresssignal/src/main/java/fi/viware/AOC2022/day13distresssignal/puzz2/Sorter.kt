package fi.viware.AOC2022.day13distresssignal.puzz2

/**
 * Sorter gets a list of signal strings as parameter.
 * It sorts this list into order specified by function isLower.
 * Method toString returns original and sorted list as a string.
 */
class Sorter(private val signalStrings: List<String>) {
    var listOfSignals:MutableList<Signal> = mutableListOf()
    var sortedListOfSignals:List<Signal> = mutableListOf()

    init {
        signalStrings.forEach {
            listOfSignals.add(Signal(it))
        }

        sortedListOfSignals = listOfSignals.sortedWith(Comparator { sig1, sig2 ->
            Int
            if (sig1.isLower(sig2)) -1 else 1
        })
    }

    /**
     * Returns sorted list of signals as strings
     */
    fun toStringSorted():String{
        return sortedListOfSignals.toString()
    }

    /**
     * Returns original list of signals as strings, followed by them in sorted order
     */
    override fun toString(): String{
        return listOfSignals.toString() + "\n" + sortedListOfSignals.toString()
    }
}