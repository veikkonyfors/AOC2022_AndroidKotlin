package fi.viware.AOC2022.day14regolithreservoir.ui.puzz1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.viware.day14regolithreservoir.databinding.FragmentPuzz1Binding

private const val TAG = "fi.viware.day14regolithreservoir.ui.puzz1.Puzz1Fragment"
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

        context?.let { puzz1ViewModel.setContext(it.filesDir) }

        _binding = FragmentPuzz1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtPuzz1Answer
        puzz1ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSolve.setOnClickListener {
            puzz1ViewModel.solve()
            Log.i(TAG,puzz1ViewModel.cave.sandPoints.toString())
            Log.i(TAG,puzz1ViewModel.cave.sandPoints.size.toString())
            //binding.textDashboard.setText(puzz1ViewModel.cave.sumValidSignalIndexes.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}