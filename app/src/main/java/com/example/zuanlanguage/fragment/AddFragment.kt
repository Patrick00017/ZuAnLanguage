package com.example.zuanlanguage.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.zuanlanguage.R
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.viewModel.DataViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var _viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewModel = DataViewModel(requireActivity().application)
        addLanguage.isEnabled = false
        val textWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                addLanguage.isEnabled = editTextTextAc.text.toString() != "" && editTextTextMeaning.text.toString() != ""
            }
        }

        editTextTextAc.addTextChangedListener(textWatcher)
        editTextTextMeaning.addTextChangedListener(textWatcher)

        addLanguage.setOnClickListener{
            val insert = Language(editTextTextAc.text.toString(), editTextTextMeaning.text.toString())
            _viewModel.insertData(insert)
            findNavController().navigateUp()
        }
    }


}