package com.example.zuanlanguage.fragment

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.text.AlteredCharSequence.make
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zuanlanguage.viewModel.DataViewModel
import com.example.zuanlanguage.R
import com.example.zuanlanguage.adapter.DataFragmentRecyclerAdapter
import com.example.zuanlanguage.database.Language
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_fragment.*
import androidx.core.view.marginTop as marginTop

class DataFragment : Fragment() {

    private lateinit var _searchLiveData: LiveData<List<Language>>

    companion object {
        fun newInstance() = DataFragment()
        val LAUCHKEY = "LauchFirst"
    }

    private lateinit var _adapter: DataFragmentRecyclerAdapter
    private lateinit var _viewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        requireActivity().bottomNav.visibility = View.VISIBLE
        requireActivity().cardView.visibility = View.VISIBLE

        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _viewModel = DataViewModel(requireActivity().application)
        _adapter = DataFragmentRecyclerAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = _adapter
        }


        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                _viewModel.deleteData(viewHolder.adapterPosition)
                Toast.makeText(requireContext(), getString(R.string.when_delete_word), Toast.LENGTH_LONG).show()
            }
        }).also { it.attachToRecyclerView(recyclerView) }


        _viewModel.fetchAllData()
        _viewModel.allDataLive?.observe(viewLifecycleOwner, Observer {
            _adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.data_fragment_toolbar_menu, menu)
        val searchView: SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.maxWidth = 500
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val s = newText?.trim()
                _searchLiveData = _viewModel.searchData(s!!)!!
                _searchLiveData.observe(viewLifecycleOwner, Observer {
                    _adapter.submitList(_searchLiveData.value)
                })
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addButton -> {
                findNavController().navigate(R.id.action_DataFragment_to_addFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(LAUCHKEY, true)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        val inputMethodManager: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }





}