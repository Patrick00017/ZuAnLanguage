package com.example.zuanlanguage.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.database.LanguageDao
import com.example.zuanlanguage.database.LanguageDatabase
import kotlinx.coroutines.launch

class DataViewModel(application: Application) : AndroidViewModel(application) {
    //data
    var allDataLive: LiveData<List<Language>> ?= null

    private var dao: LanguageDao ?= null
    private var database: LanguageDatabase = LanguageDatabase.get(application)

    init {
        dao = database.languageDao()
    }

    fun fetchAllData(){
        allDataLive = dao?.fetchAllData()
    }

    fun insertData(vararg language: Language){
        viewModelScope.launch {
            dao?.insertData(*language)
        }
    }
}