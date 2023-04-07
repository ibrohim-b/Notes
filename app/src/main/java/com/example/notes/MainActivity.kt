package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.room.room.DatabaseRoom
import com.example.room.room.RoomEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = DatabaseRoom.getInstance(this)
        val roomDao = db.roomDao()
        GlobalScope.launch(Dispatchers.Main) {
            (0..15).forEach {
                roomDao.insertAll(RoomEntity(uid = it, title = "Tolya$it", note = "Valie Penfo", time = "Pinchtskam chit$it"))
            }
            val notes: List<RoomEntity> = roomDao.getAll()
            val notesRv = findViewById<RecyclerView>(R.id.notesRV)
            fun new(id: Int){
                deleteItem(id)
                notes.toMutableList().removeAt(id)
            }
            var adapter = NoteAdapter(){
                new(it)
            }

            notesRv.adapter = adapter
            adapter.setNotes(notes)
            Toast.makeText(this@MainActivity, notes.size.toString() , Toast.LENGTH_SHORT).show()
        }

    }
    fun deleteItem(id: Int) {
        GlobalScope.launch {
            val db = DatabaseRoom.getInstance(this@MainActivity)
            val roomDao = db.roomDao()
            roomDao.delete(id)
        }
    }

}