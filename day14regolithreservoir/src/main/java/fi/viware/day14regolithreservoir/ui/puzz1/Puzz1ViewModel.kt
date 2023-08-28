package fi.viware.day14regolithreservoir.ui.puzz1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.viware.day14regolithreservoir.Cave
import java.io.File

class Puzz1ViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "Puzz1 Answer"
    }
    val text: LiveData<String> = _text
    private lateinit var filesDir: File
    private lateinit var pathLines: List<String>
    lateinit var cave: Cave

    fun solve(){
        val file = File(
            filesDir,
            "input"
        )
        pathLines = file.readLines()
        cave = Cave(pathLines)
        cave.fill()
        _text.apply { value = cave.sandPoints.size.toString() } // LiveData will be observed in Puzz1Fragment
    }
    fun setContext(filesDir: File) {
        this.filesDir = filesDir
    }
}