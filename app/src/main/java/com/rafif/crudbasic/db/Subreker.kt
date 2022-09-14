package com.rafif.crudbasic.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subreker_data_table")
data class Subreker(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subreker_id")
    var id : Int,

    @ColumnInfo(name = "subreker_name")
    var name : String,

    @ColumnInfo(name = "subreker_email")
    var email: String
)