package fi.viware.AOC2022.day13distresssignal.ui.puzz1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.viware.AOC2022.day13distresssignal.databinding.FragmentPuzz1Binding

class Puzz1Fragment : Fragment() {

    private var _binding: FragmentPuzz1Binding? = null

    lateinit var puzz1ViewModel: Puzz1ViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        puzz1ViewModel =
            ViewModelProvider(this).get(Puzz1ViewModel::class.java)

        _binding = FragmentPuzz1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        puzz1ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        puzz1ViewModel.distressSignalHandler.compare()
        puzz1ViewModel.distressSignalHandler.countSumValidSignalIndexes()

        binding.btnSolvePuzz1.setOnClickListener {
            Log.i("Puzz1Fragment",puzz1ViewModel.distressSignalHandler.sumValidSignalIndexes.toString())
            binding.textDashboard.setText(puzz1ViewModel.distressSignalHandler.sumValidSignalIndexes.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}