package com.example.secretnotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secretnotes.databinding.ActivityTambahNotesBinding

class TambahNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}