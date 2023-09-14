package fi.viware.AOC2022.day15beaconexclusionzone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import fi.viware.AOC2022.day15beaconexclusionzone.R
import fi.viware.AOC2022.day15beaconexclusionzone.databinding.FragmentPuzz2Binding

private const val TAG = "fi.viware.AOC2022.day14regolithreservoir.ui.Puzz2Fragment"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Puzz2dFragment : Fragment() {

    private var _binding: FragmentPuzz2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: Puzz2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPuzz2Binding.inflate(inflater, container, false)

        viewModel = Puzz2ViewModel()
        context?.let { viewModel.setFilesDir(it.filesDir) }

        val txtSolution = binding.txtSolution
        viewModel.live_text.observe(viewLifecycleOwner) {
            txtSolution.text = it
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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