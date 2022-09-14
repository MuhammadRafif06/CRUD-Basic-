package com.rafif.crudbasic.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface SubrekerDAO {

    //disini kita akan mengakses db dengan room

    //cuz it might lock db acces on the main thread
    //suspend can be paused an resumed at a later time
    @Insert
    suspend fun insertSubreker(subreker: Subreker) : Long

    @Update
    suspend fun updateSubreker(subreker: Subreker)

    @Delete
    suspend fun deleteSubreker(subreker: Subreker)

    @Query("DELETE FROM subreker_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subreker_data_table")
    fun getAllSubreker(): LiveData<List<Subreker>>
}