package fi.viware.AOC2022.day13distresssignal.ui.puzz2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.viware.AOC2022.day13distresssignal.puzz2.Sorter
import java.io.File

class Puzz2ViewModel() : ViewModel() {

    // Pass answer as LiveData.
    // getAnswer will update the answer, which will be reflected
    // to fragments textView by declaring it to be observed for changes
    // in modelview's value.
    private val _answer = MutableLiveData<String>().apply {
        value = "This is Fragment for solving Puzzle2\nAnswer will be presented here."
    }
    val answer: LiveData<String> = _answer

    private val _context = MutableLiveData<Context?>()

    fun setContext(context: Context?) {
        _context.value = context
        //_context.apply { value = context }
    }

    fun getAnswer(){

        println(_context.value?.filesDir.toString())

        val file = File(
            _context.value?.filesDir,
            "input"
        )

        val signalStringsFromFile = file.readLines()
        val nonEmptySignalStringsFromFile = signalStringsFromFile.filter {
            it.isNotEmpty()
        }
        val dividerPackets = listOf("[2]", "[6]")
        var signalStrings: List<String> = nonEmptySignalStringsFromFile + dividerPackets

        val sorter = Sorter(signalStrings)

        val n = sorter.sortedListOfSignals.indexOfFirst {
            it.listSignal.toString() == "[2]"
        }
        val m = sorter.sortedListOfSignals.indexOfFirst {
            it.listSignal.toString() == "[6]"
        }
        println("$n, $m, ${(n + 1) * (m + 1)}")
        _answer.value = ((n + 1) * (m + 1)).toString()
        //return (n + 1) * (m + 1)
    }
}