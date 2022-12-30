package fi.viware.day12hillclimbing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import fi.viware.day12hillclimbing.databinding.FragmentSecondBinding
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTo1stPuzzle.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.button2ndSolution.setOnClickListener {
            lateinit var hillHeight: List<String>
            lateinit var hill:Hill

            val file = File(
                activity?.applicationContext?.filesDir ,
                "input"
            )
            hillHeight = file.readLines()
            hill = Hill(hillHeight)

            println(hill.cost)
            println(hill.hillHeigths)
            while(hill.carryOn){
                hill.iterate()
            }

            hill.cost.forEach { println(Arrays.deepToString(it)) }
            hill.hillHeigths.forEach { println(it) }

            binding.secondAnswer.setText(hill.solve2ndPuzzle().toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}