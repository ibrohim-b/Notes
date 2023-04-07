package com.example.room.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class RoomEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name="title") val title: String?,
    @ColumnInfo(name="note") val note: String?,
    @ColumnInfo(name="time") val time: String
)