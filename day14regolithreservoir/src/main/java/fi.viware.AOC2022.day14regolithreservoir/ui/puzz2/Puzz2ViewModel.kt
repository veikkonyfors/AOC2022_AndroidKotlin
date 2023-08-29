package fi.viware.AOC2022.day14regolithreservoir.ui.puzz2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.viware.day14regolithreservoir.Cave
import fi.viware.day14regolithreservoir.CavePuzz2
import java.io.File

class Puzz2ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Puzz2 Answer"
    }
    val text: LiveData<String> = _text

    lateinit var filesDir: File
    lateinit var pathLines: List<String>
    lateinit var cave: CavePuzz2

    fun solve(){
        val file = File(
            filesDir,
            "input"
        )
        pathLines = file.readLines()
        cave = CavePuzz2(pathLines)
        cave.fill()
        _text.apply { value = cave.sandPoints.size.toString() } // LiveData will be observed in Puzz1Fragment
    }
    fun setContext(filesDir: File) {
        this.filesDir = filesDir
    }
}