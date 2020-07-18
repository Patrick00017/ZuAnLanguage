package com.example.zuanlanguage.fragment
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zuanlanguage.viewModel.DataViewModel
import com.example.zuanlanguage.R
import com.example.zuanlanguage.adapter.DataFragmentRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_fragment.*

class DataFragment : Fragment() {

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

//        if (savedInstanceState == null) {
//            val a = Language("dnm", "叼你妈")
//            val b = Language("nmd", "你妈的")
//            _viewModel.insertData(a,b)
//        }

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
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
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



}