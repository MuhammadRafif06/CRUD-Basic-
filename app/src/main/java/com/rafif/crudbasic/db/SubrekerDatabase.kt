package com.rafif.crudbasic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//to represent the actual database
@Database(entities = [Subreker::class], version = 1)
abstract class SubrekerDatabase : RoomDatabase() {

    abstract val subrekerDAO : SubrekerDAO

    // we should only use one interface of
    // roomdatabase for entire app
    // to avoid unexcpected error

    // we create singleton as companion object
    companion object{
        // this reference to the SubrekerDatabase
        @Volatile
        // make field immidiately made visible to other thread
        private var INSTANCE : SubrekerDatabase? = null

        fun getInstance(context: Context) : SubrekerDatabase{
            // in this block we add syncronized block
            // lock to represent SubrekerDatabase
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubrekerDatabase::class.java,
                        "subreker_data.db"
                    ).build()
                 }
                return instance
            }
        }
    }

}