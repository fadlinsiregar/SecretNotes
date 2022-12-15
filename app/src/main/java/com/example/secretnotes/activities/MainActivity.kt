package com.example.secretnotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secretnotes.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambahNotes.setOnLongClickListener {
            Snackbar.make(binding.root, "Tambah Notes!", Snackbar.LENGTH_LONG).show()
            true
        }
    }
}