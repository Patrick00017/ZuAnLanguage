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
import java.util.regex.Pattern

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

    fun searchData(pattern: String): LiveData<List<Language>>? {
        return dao?.searchData("%${pattern}%")
    }

    fun deleteData(position: Int){
        viewModelScope.launch {
            val language = allDataLive?.value?.get(position)
            if(language != null){
                dao?.deleteData(language)
            }
        }
    }

    fun updateData(vararg data: Language){
        viewModelScope.launch {
            dao?.updateData(*data)
        }
    }
}