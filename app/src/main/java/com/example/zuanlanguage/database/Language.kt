package com.example.zuanlanguage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Language")
data class Language(
    @ColumnInfo(name = "acronym")
    var acronym: String,
    @ColumnInfo(name = "meaning")
    var meaning: String,
    @ColumnInfo(name = "isLiked")
    var isLiked: Boolean){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
        get() = field
        set(value) {
            field = value
        }
}
