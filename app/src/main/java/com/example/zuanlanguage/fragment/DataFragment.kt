package com.example.zuanlanguage.fragment
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.zuanlanguage.viewModel.DataViewModel
import com.example.zuanlanguage.R
import com.example.zuanlanguage.adapter.DataFragmentRecyclerAdapter
import com.example.zuanlanguage.database.LanguageDao
import com.example.zuanlanguage.database.LanguageDatabase
import kotlinx.android.synthetic.main.data_fragment.*

class DataFragment : Fragment() {

    companion object {
        fun newInstance() = DataFragment()
    }

    private lateinit var _database: LanguageDatabase
    private lateinit var _dao: LanguageDao
    private lateinit var _adapter: DataFragmentRecyclerAdapter
    private val _viewModel by viewModels<DataViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _database = Room.databaseBuilder(requireContext(), LanguageDatabase::class.java, "database").build()
        _dao = _database.languageDao()

        _adapter = DataFragmentRecyclerAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = _adapter
        }

        _viewModel.dao = _dao

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
                findNavController().navigate(R.id.action_DataFragment_to_addFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}