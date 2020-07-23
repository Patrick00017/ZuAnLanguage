package com.example.zuanlanguage.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.zuanlanguage.R
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.viewModel.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.genarate_fragment.*

class GenarateFragment : Fragment() {

    companion object {
        fun newInstance() = GenarateFragment()
    }

    private lateinit var dataViewModel: DataViewModel
    private var genarateData: LiveData<List<Language>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().bottomNav.visibility = View.VISIBLE
        requireActivity().cardView.visibility = View.VISIBLE
        return inflater.inflate(R.layout.genarate_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataViewModel = DataViewModel(requireActivity().application)
        genarateButton.isEnabled = false
        copyButton.isEnabled = false
        likeButton.isEnabled =false
        askTextView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                genarateButton.isEnabled = !TextUtils.isEmpty(s.toString().trim())
            }
        })

//        genarateButton.setOnClickListener {
//            genarateData = dataViewModel.searchData(askTextView.text.toString().trim())!!
//            AnsTextView.text = R.string.loading.toString()
//        }
//
//        genarateData?.observe(viewLifecycleOwner, Observer {
//            AnsTextView.text = it.get(0).meaning
//        })

    }

}