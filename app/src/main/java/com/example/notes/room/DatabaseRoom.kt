package com.example.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomEntity::class], version = 1)
abstract class DatabaseRoom: RoomDatabase() {

    abstract fun roomDao() : RoomDao
    companion object{
        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        fun getInstance(context : Context) : DatabaseRoom {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseRoom::class.java,
                    "database_name"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}