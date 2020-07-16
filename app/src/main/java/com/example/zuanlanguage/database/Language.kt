package com.example.zuanlanguage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Language")
class Language(acronym: String, meaning: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    get() = field
    set(value) {
        field = value
    }

    @ColumnInfo(name = "acronym")
    var acronym: String = acronym
        get() = field
    set(value){
        field = value
    }

    @ColumnInfo(name = "meaning")
    var meaning: String = meaning
        get() = field
        set(value){
            field = value
        }

}