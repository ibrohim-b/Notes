package com.example.room.room

import androidx.room.*

@Dao

interface RoomDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll() : List<RoomEntity>

//    @Query("SELECT * WHERE ")
//    suspend fun loadAllByIds(userIds: IntArray): List<RoomEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(notes: RoomEntity)

    @Query("DELETE FROM notes WHERE uid = :id")
    suspend fun delete(id: Int)

}