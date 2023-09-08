package fi.viware.AOC2022.day15beaconexclusionzone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.viware.AOC2022.day15beaconexclusionzone.BeaconExclusionZone
import java.io.File

class Puzz1ViewModel: ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "Puzz1 Answer"
    }
    val text: LiveData<String> = _text
    private lateinit var filesDir: File
    private lateinit var pathLines: List<String>
    lateinit var cave: BeaconExclusionZone

    fun solve(){
        val input = File(
            filesDir,
            "input_test"
        )

        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZone(sensorDataLines, 10)

        _text.apply { value = beaconExclusionZone.noBeacons.size.toString() } // LiveData will be observed in Puzz1Fragment
    }
    fun setFilesDir(filesDir: File) {
        this.filesDir = filesDir
    }
}