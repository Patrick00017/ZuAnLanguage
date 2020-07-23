package com.example.zuanlanguage.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.regex.Pattern

@Dao
interface LanguageDao {
    @Query("select * from Language ORDER BY id DESC")
    fun fetchAllData(): LiveData<List<Language>>

    @Insert
    suspend fun insertData(vararg data: Language)

    @Delete
    suspend fun deleteData(data: Language)

    @Update
    suspend fun updateData(vararg data: Language)

    @Query("SELECT * FROM Language WHERE acronym like :pattern ORDER BY id DESC")
    fun searchData(pattern: String): LiveData<List<Language>>

    @Query("SELECT * FROM Language WHERE isLiked == 1 ORDER BY id DESC")
    fun getMineData(): LiveData<List<Language>>

}