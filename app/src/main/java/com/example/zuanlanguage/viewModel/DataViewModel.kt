package com.example.zuanlanguage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.database.LanguageDao

class DataViewModel() : ViewModel() {
    var _dao: LanguageDao ?= null
    var _allDataLive: LiveData<List<Language>> ?= null

    public fun fetchAllData(){
        _allDataLive = _dao?.fetchAllData()
    }
}