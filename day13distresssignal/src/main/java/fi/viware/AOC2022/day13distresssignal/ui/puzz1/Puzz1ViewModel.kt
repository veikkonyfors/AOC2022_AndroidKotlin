package fi.viware.AOC2022.day13distresssignal.ui.puzz1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.viware.AOC2022.day13distresssignal.DistressSignalHandler

class Puzz1ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Fragment to solve Puzzle1"
    }
    val text: LiveData<String> = _text

    val distressSignalHandler=DistressSignalHandler()
}