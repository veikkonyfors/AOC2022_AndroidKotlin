package fi.viware.AOC2022.day13distresssignal.ui.puzz2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.viware.AOC2022.day13distresssignal.databinding.FragmentPuzz2Binding

class Puzz2Fragment : Fragment() {

    private var _binding: FragmentPuzz2Binding? = null

    lateinit var puzz2ViewModel: Puzz2ViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val tst = "kukkuu"

        puzz2ViewModel =
            ViewModelProvider(this).get(Puzz2ViewModel::class.java)
        puzz2ViewModel.setContext(activity?.applicationContext)


        _binding = FragmentPuzz2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtAnswerView: TextView = binding.txtAnswer
        puzz2ViewModel.answer.observe(viewLifecycleOwner) {
            txtAnswerView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSolvePuzz2.setOnClickListener {
            Log.i("Puzz2Fragment",puzz2ViewModel.getAnswer().toString())
            //binding.txtAnswer.setText(puzz2ViewModel.getAnswer().toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}