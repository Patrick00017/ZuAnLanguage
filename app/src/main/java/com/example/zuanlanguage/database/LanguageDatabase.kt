package com.example.zuanlanguage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ Language::class ], version = 1, exportSchema = false)
abstract class LanguageDatabase: RoomDatabase() {
    companion object{
        private var instance: LanguageDatabase? = null
        @Synchronized
        fun get(context: Context): LanguageDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context, LanguageDatabase::class.java, "database").build()
            }
            return instance as LanguageDatabase
        }
    }


//   kotlin singleton
//    class Config{
//        companion object{
//            private var instance:Config?=null
//            @Synchronized
//            fun get():Config{
//                if(nnull==instance) instance=Config()
//                return instance
//            }
//        }
//    }

    abstract fun languageDao(): LanguageDao
}