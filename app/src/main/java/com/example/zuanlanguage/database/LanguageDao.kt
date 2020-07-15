package com.example.zuanlanguage.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LanguageDao {
    @Query("select * from Language")
    suspend fun fetchAllData(): LiveData<List<Language>>

    @Insert
    suspend fun insertData(vararg data: Language)

    @Delete
    suspend fun deleteData(vararg data: Language)

    @Update
    suspend fun updateData(vararg data: Language)
}