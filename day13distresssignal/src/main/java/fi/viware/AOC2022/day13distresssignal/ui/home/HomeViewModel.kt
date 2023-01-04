package fi.viware.AOC2022.day13distresssignal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is DistressSignalHandler.\n Use Puzzle1 and Puzzle2 down there to go to solve them"
    }
    val text: LiveData<String> = _text
}