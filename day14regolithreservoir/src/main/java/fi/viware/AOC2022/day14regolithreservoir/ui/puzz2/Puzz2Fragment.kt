package fi.viware.AOC2022.day14regolithreservoir.ui.puzz2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.viware.day14regolithreservoir.databinding.FragmentPuzz2Binding

private const val TAG = "fi.viware.AOC2022.day14regolithreservoir.ui.puzz1.Puzz2Fragment"
class Puzz2Fragment : Fragment() {

    private var _binding: FragmentPuzz2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var puzz2ViewModel: Puzz2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        puzz2ViewModel =
            ViewModelProvider(this).get(Puzz2ViewModel::class.java)

        context?.let { puzz2ViewModel.setContext(it.filesDir) }

        _binding = FragmentPuzz2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtPuzz2Answer
        puzz2ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSolvePuzz2.setOnClickListener {
            puzz2ViewModel.solve()
            Log.i(TAG, puzz2ViewModel.cave.sandPoints.toString())
            Log.i(TAG, puzz2ViewModel.cave.sandPoints.size.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}