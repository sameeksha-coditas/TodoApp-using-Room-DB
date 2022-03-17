package com.example.notesapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplication.databinding.ActivityMainBinding
import com.example.notesapplication.modal.Note
import com.example.notesapplication.viewmodel.NoteViewModal

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModal: NoteViewModal


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.logInfo("Inside MainActivity onCreate()")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       // binding.idRVNotes.layoutManager = LinearLayoutManager(this)

        val notesRVAdapter = NoteRVAdapter(this, this)//initialising NoteRVAdapter class
       // binding.idRVNotes.adapter = notesRVAdapter
        binding.viewPager.adapter=notesRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModal::class.java]

        viewModal.allNotes.observe(this) { list ->
                Logger.logInfo("Inside observe method in MainActivity")
            list?.let {
                notesRVAdapter.updateList(it) //this will add data to our NoteRVAdapter list which we have created in the NoteRVAdapter class
            }
        }  //calling observe method to observe the changes in this livedata
        binding.idFABAddNote.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.logInfo("Inside MainActivity onDestroy()")
    }

    override fun onPause() {
        super.onPause()
        Logger.logInfo("Inside MainActivity onPause()")
    }

    override fun onResume() {
        super.onResume()
        Logger.logInfo("Inside MainActivity onResume()")
    }

    override fun onStart() {
        super.onStart()
        Logger.logInfo("Inside MainActivity onStart()")
    }

    override fun onStop() {
        super.onStop()
        Logger.logInfo("Inside MainActivity onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Logger.logInfo("Inside MainActivity onRestart()")
    }


    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle}Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }
}