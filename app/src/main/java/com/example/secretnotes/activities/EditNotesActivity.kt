package com.example.secretnotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secretnotes.databinding.ActivityEditNotesBinding

class EditNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}