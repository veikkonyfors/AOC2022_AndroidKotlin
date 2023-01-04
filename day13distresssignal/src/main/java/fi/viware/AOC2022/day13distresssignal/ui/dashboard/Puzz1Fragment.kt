package fi.viware.AOC2022.day13distresssignal.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.viware.AOC2022.day13distresssignal.databinding.FragmentDashboardBinding

class Puzz1Fragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    lateinit var dashboardViewModel:Puzz1ViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(Puzz1ViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.distressSignalHandler.compare()
        dashboardViewModel.distressSignalHandler.countSumValidSignalIndexes()

        binding.btnSolvePuzz1.setOnClickListener {
            Log.i("Puzz1Fragment",dashboardViewModel.distressSignalHandler.sumValidSignalIndexes.toString())
            binding.textDashboard.setText(dashboardViewModel.distressSignalHandler.sumValidSignalIndexes.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}