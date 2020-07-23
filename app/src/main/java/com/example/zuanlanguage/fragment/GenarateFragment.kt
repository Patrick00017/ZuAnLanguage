package com.example.zuanlanguage.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.zuanlanguage.R
import com.example.zuanlanguage.adapter.DataFragmentRecyclerAdapter
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.viewModel.DataViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.genarate_fragment.*

class GenarateFragment : Fragment() {

    companion object {
        fun newInstance() = GenarateFragment()
    }

    private lateinit var dataViewModel: DataViewModel
    private lateinit var currentLanguage: Language

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

        genarateButton.setOnClickListener {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view?.windowToken, 0)
            dataViewModel.genarateData(askTextView.text.toString().trim())
            AnsTextView.text = resources.getString(R.string.loading)
            dataViewModel.genarateLiveData?.observe(viewLifecycleOwner, Observer {
                if(it.isEmpty()){
                    AnsTextView.text = resources.getString(R.string.empty)
                    copyButton.isEnabled = false
                    likeButton.isEnabled = false
                    Picasso.get().load(R.drawable.like_unclick).into(likeButton as ImageView)
                }
                else
                {
                    AnsTextView.text = it[0].meaning
                    copyButton.isEnabled = true
                    likeButton.isEnabled = true
                    currentLanguage = it[0]
                    if(currentLanguage.isLiked) Picasso.get().load(R.drawable.like_clicked).into(likeButton as ImageView)
                    else Picasso.get().load(R.drawable.like_unclick).into(likeButton as ImageView)
                }
            })
        }

        copyButton.setOnClickListener {
            val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(DataFragmentRecyclerAdapter.CLIPDATA_LABEL, AnsTextView.text.toString())
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(requireContext(), R.string.copy_successful, Toast.LENGTH_LONG).show()
        }

        likeButton.setOnClickListener {
            if(currentLanguage.isLiked){
                Picasso.get().load(R.drawable.like_unclick).into(it as ImageView)
                currentLanguage.isLiked = false
                dataViewModel.updateData(currentLanguage)
                Toast.makeText(requireContext(), R.string.undoLike, Toast.LENGTH_SHORT).show()
            }else{
                Picasso.get().load(R.drawable.like_clicked).into(it as ImageView)
                currentLanguage.isLiked = true
                dataViewModel.updateData(currentLanguage)
                Toast.makeText(requireContext(), R.string.doLike, Toast.LENGTH_SHORT).show()
            }
        }


    }

}