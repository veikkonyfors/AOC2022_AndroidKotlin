package fi.viware.test.day16proboscideavolcanium.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fi.viware.test.day16proboscideavolcanium.RouteFinder
import java.io.File

class Puzz1ViewModel {

    var test = true

    private lateinit var filesDir: File

    private var _answer = MutableLiveData<String>().apply {
        value = "Puzz1 Answer"
    }
    val answer: LiveData<String> = _answer

    fun solve() {

        var fileName: String

        when {
            test -> fileName = "input_test"
            else -> fileName = "input"
        }
        val inputFile = File(filesDir,fileName)
        val inputLines = inputFile.readLines()
        val routeFinder = RouteFinder(inputLines)
        _answer.value = routeFinder.solvePuzz1().toString()
    }

    fun setFilesDir(fd: File) {
        filesDir = fd
    }
}