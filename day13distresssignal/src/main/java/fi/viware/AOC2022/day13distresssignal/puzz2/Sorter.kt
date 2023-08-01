package fi.viware.AOC2022.day13distresssignal.puzz2

class Sorter(val signalStrings: List<String>) {
    var listOfSignals:MutableList<Signal> = mutableListOf()
    var sortedListOfSignals:List<Signal> = mutableListOf()

    init {
        signalStrings.forEach {
            listOfSignals.add(Signal(it))
            sortedListOfSignals = listOfSignals.sortedWith(Comparator { sig1, sig2 ->
                Int
                if (sig1.isLowerOrEqual(sig2)) -1 else 1
            })

        }
    }

    override fun toString(): String{
        return listOfSignals.toString() + "\n" + sortedListOfSignals.toString()
    }
}