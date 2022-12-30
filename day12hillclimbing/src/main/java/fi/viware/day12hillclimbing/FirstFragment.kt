package fi.viware.day12hillclimbing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import fi.viware.day12hillclimbing.databinding.FragmentFirstBinding
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTo2ndPuzz.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.button1stSolution.setOnClickListener {
            val snackbar=Snackbar.make(view, "Solution for first puzzle day12 will be called", Snackbar.LENGTH_LONG)
                .setAction("1stPuzzle") {

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

                    binding.firstAnswer.setText(hill.pathCost.toString())
                }

            snackbar.show()


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}