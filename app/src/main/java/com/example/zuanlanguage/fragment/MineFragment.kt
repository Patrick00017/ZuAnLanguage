package com.example.zuanlanguage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zuanlanguage.viewModel.MineViewModel
import com.example.zuanlanguage.R
import com.example.zuanlanguage.adapter.MineRecyclerAdapter
import com.example.zuanlanguage.viewModel.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.example.zuanlanguage.database.Language
import kotlinx.android.synthetic.main.mine_fragment.*

class MineFragment : Fragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    private lateinit var dataViewModel: DataViewModel
    private lateinit var _adapter: MineRecyclerAdapter
    private lateinit var _mineLiveData: LiveData<List<Language>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().bottomNav.visibility = View.VISIBLE
        requireActivity().cardView.visibility = View.VISIBLE
        return inflater.inflate(R.layout.mine_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataViewModel = DataViewModel(requireActivity().application)
        //todo: when getMineData return null
        _adapter = MineRecyclerAdapter(requireActivity().application)
        mineRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = _adapter
        }
        _mineLiveData = dataViewModel.getMineData()!!
        _mineLiveData.observe(viewLifecycleOwner, Observer {
            _adapter.submitList(it)
        })
    }

}