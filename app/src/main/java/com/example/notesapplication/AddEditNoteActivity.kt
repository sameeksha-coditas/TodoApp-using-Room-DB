package com.example.notesapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplication.databinding.ActivityAddEditNoteBinding
import com.example.notesapplication.modal.Note
import com.example.notesapplication.viewmodel.NoteViewModal
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var viewModal: NoteViewModal
    private lateinit var binding: ActivityAddEditNoteBinding
    private var noteID = -1

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.logInfo("Inside EditActivity onCreate()")
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModal = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModal::class.java]

        val noteType = intent.getStringExtra("noteType")
        val noteID = intent.getIntExtra("noteID", -1)
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")

            binding.idBtnAddUpdate.text = getString(R.string.update_note)
            binding.idEditNoteTitle.setText(noteTitle)
            binding.idEditNoteDescription.setText(noteDesc)
        } else {
            binding.idBtnAddUpdate.text = getString(R.string.save_note)
        }

        binding.idBtnAddUpdate.setOnClickListener {
            val noteTitle = binding.idEditNoteTitle.text.toString()
            val noteDescription = binding.idEditNoteDescription.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("DD MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    viewModal.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("DD MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added..", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))

        }
    }

    override fun onStart() {
        super.onStart()
        Logger.logInfo("Inside EditActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Logger.logInfo("Inside EditActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Logger.logInfo("Inside EditActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Logger.logInfo("Inside EditActivity onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Logger.logInfo("Inside EditActivity onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.logInfo("Inside EditActivity onDestroy()")
    }
}