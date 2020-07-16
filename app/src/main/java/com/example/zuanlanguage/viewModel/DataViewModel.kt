package com.example.zuanlanguage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.database.LanguageDao
import kotlinx.coroutines.launch

class DataViewModel() : ViewModel() {
    var dao: LanguageDao ?= null
    var allDataLive: LiveData<List<Language>> ?= null

    public fun fetchAllData(){
        allDataLive = dao?.fetchAllData()
    }

    fun insertData(vararg language: Language){
        viewModelScope.launch {
            dao?.insertData(*language)
        }
    }
}