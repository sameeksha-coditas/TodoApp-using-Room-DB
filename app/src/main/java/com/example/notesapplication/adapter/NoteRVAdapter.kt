package com.example.notesapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplication.modal.Note

class NoteRVAdapter(
    //val context: Context,
    private val noteClickInterface: NoteClickInterface,
    private val noteClickDeleteInterface: NoteClickDeleteInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {
    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNoteTitle)!!
        val timeTV = itemView.findViewById<TextView>(R.id.idTVTimeStamp)!!
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)!!
       // val pos=adapterPosition

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.text = allNotes[position].noteTitle
        holder.timeTV.text = "Last Updated: " + allNotes[position].timeStamp

        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }

//        fun returnPos():Int{
//            return position
//        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {

        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()      //to notify the adapter
       // notifyItemChanged()
        Logger.logInfo("Inside updateList()")
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {

    fun onNoteClick(note: Note)
}