package com.example.zuanlanguage.fragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.zuanlanguage.viewModel.DataViewModel
import com.example.zuanlanguage.R
import com.example.zuanlanguage.database.LanguageDao
import com.example.zuanlanguage.database.LanguageDatabase
import com.example.zuanlanguage.database.Language

class DataFragment : Fragment() {

    companion object {
        fun newInstance() = DataFragment()
    }

    private lateinit var _database: LanguageDatabase
    private lateinit var _dao: LanguageDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _database = Room.databaseBuilder(requireContext(), LanguageDatabase::class.java, "database").build()
        _dao = _database.languageDao()
        val a = Language("dnm", "叼你妈")
        val b = Language("nmd", "你妈的")

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(DataViewModel::class.java).apply { this.dao = _dao }
//        viewModel.insertData(a,b)
        viewModel.fetchAllData()
        viewModel.allDataLive?.observe(viewLifecycleOwner, Observer {
            Log.d("mylog", "onActivityCreated: ${it}")
        })
    }

}