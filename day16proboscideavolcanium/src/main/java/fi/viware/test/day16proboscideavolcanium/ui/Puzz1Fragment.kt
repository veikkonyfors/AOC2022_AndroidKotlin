package fi.viware.test.day16proboscideavolcanium.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import fi.viware.test.day16proboscideavolcanium.R
import fi.viware.test.day16proboscideavolcanium.databinding.FragmentPuzz1Binding

class Puzz1Fragment :Fragment(){

    private lateinit var viewModel: Puzz1ViewModel

    private lateinit var binding: FragmentPuzz1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPuzz1Binding.inflate(inflater, container, false)
        val view = binding.root
        //val view = inflater.inflate(R.layout.fragment_puzz1, container, false)

        viewModel = Puzz1ViewModel()
        context?.let { viewModel.setFilesDir(it.filesDir) }

        val txtAnswer = view.findViewById<TextView>(R.id.txtAnswer)
        viewModel.answer.observe(viewLifecycleOwner) {
                txtAnswer.text = it
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioButtonTest.requestFocus()
        viewModel.test = true

        // With binding enabled, one can still use traditional approach
        //binding.btnSolve.setOnClickListener { .... }
        view.findViewById<Button>(R.id.btnSolve).setOnClickListener {
            println(binding.radioButtonTest.isFocused)
            viewModel.solve()
        }

        binding.radioButtonTest.setOnClickListener{
            viewModel.test = true
        }

        binding.radioButtonProd.setOnClickListener{
            viewModel.test = false
        }

    }

}

