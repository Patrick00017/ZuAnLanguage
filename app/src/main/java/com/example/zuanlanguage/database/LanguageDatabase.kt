package com.example.zuanlanguage.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ Language::class ], version = 1, exportSchema = false)
abstract class LanguageDatabase: RoomDatabase() {
    abstract fun getLanguageDao(): LanguageDao
}