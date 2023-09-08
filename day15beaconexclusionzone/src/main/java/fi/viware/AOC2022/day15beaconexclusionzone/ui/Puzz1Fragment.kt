package fi.viware.AOC2022.day15beaconexclusionzone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import fi.viware.AOC2022.day15beaconexclusionzone.R
import fi.viware.AOC2022.day15beaconexclusionzone.databinding.FragmentPuzz1Binding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Puzz1Fragment : Fragment() {

    private var _binding: FragmentPuzz1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: Puzz1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPuzz1Binding.inflate(inflater, container, false)

        viewModel = Puzz1ViewModel()
        context?.let { it1 -> viewModel.setFilesDir(it1.filesDir) }

        val txtSolution = binding.txtSolution
        viewModel.text.observe(viewLifecycleOwner) {
            txtSolution.text = it
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btnSolve.setOnClickListener {
            viewModel.solve()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}