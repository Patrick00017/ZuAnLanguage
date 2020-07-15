package com.example.zuanlanguage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Language")
data class Language (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "acronym")
    val acronym: String,
    @ColumnInfo(name = "meaning")
    val meaning: String
)