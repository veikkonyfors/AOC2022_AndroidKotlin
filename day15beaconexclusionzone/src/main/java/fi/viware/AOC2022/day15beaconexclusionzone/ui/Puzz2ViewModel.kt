package fi.viware.AOC2022.day15beaconexclusionzone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.viware.AOC2022.day15beaconexclusionzone.BeaconExclusionZone
import fi.viware.AOC2022.day15beaconexclusionzone.BeaconExclusionZonePuzzManhattan
import java.io.File
import java.math.BigInteger

private const val TAG = "fi.viware.AOC2022.day14regolithreservoir.ui.Puzz2ViewModel"
class Puzz2ViewModel: ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "Puzz2 Answer"
    }
    val live_text: LiveData<String> = _text

    private lateinit var filesDir: File
    private lateinit var pathLines: List<String>
    lateinit var cave: BeaconExclusionZone

    fun solve(){
        val input = File(
            filesDir,
            "input"
        )

        val sensorDataLines = input.readLines()

        val beaconExclusionZone = BeaconExclusionZonePuzzManhattan(sensorDataLines)

        val availablePoints =
            beaconExclusionZone.availableBeaconPoints_surroundingpoints_manhattandistance_impl(0, 4000000)
        if (availablePoints.size != 1) {
            _text.apply {value = "Puzz2ViewModel problem: more than one point found!" } // LiveData will be observed in Puzz1Fragment
        } else {
            val availablePoint = availablePoints.first()
            val answer =
                BigInteger.valueOf(availablePoint.x.toLong()) * BigInteger.valueOf(4000000) + BigInteger.valueOf(availablePoint.y.toLong())
            _text.apply {
                value = answer.toString()
            } // LiveData will be observed in Puzz1Fragment
        }


    }
    fun setFilesDir(filesDir: File) {
        this.filesDir = filesDir
    }
}