package fi.viware.test.day16proboscideavolcanium.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fi.viware.test.day16proboscideavolcanium.R
import fi.viware.test.day16proboscideavolcanium.databinding.ActivityProboscideaVolcaniumBinding

class ProboscideaVolcaniumActivity : AppCompatActivity() {

    private lateinit var puzz1Fragment: Puzz1Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityProboscideaVolcaniumBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_proboscidea_volcanium)
        setContentView(binding.root)

        puzz1Fragment = Puzz1Fragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, puzz1Fragment)
            .commitNow()
    }
}