package com.example.notes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.room.room.DatabaseRoom
import com.example.room.room.RoomDao
import com.example.room.room.RoomEntity

class NoteAdapter(val itemOnClick:(Int, NoteAdapter) -> Unit) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    val notes: MutableList<RoomEntity> = emptyList<RoomEntity>().toMutableList()
    fun setNotes(notes : List<RoomEntity>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val titleTV : TextView = view.findViewById(R.id.title)
        val descTV : TextView = view.findViewById(R.id.desc)
        val timeTV : TextView = view.findViewById(R.id.time)
        val idTV : TextView = view.findViewById(R.id.idN)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create new view with UI of weather item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("onBindViewHolder",  notes.size.toString())
        holder.titleTV.text = notes[position].title
        holder.descTV.text = notes[position].note
        holder.timeTV.text = notes[position].time
        holder.idTV.text = notes[position].uid.toString()

        holder.itemView.setOnClickListener{
            itemOnClick.invoke(notes[position].uid,this)
        }
    }
    override fun getItemCount(): Int {
        return notes.size
    }
}
