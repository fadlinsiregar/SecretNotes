package com.example.secretnotes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.secretnotes.databinding.ActivityTambahNotesBinding
import com.example.secretnotes.models.Responses
import com.example.secretnotes.services.NotesServices
import com.example.secretnotes.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class TambahNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambahNotes.setOnClickListener {
            val judulNotes = binding.etJudul.text.toString()
            val isiNotes = binding.etIsiNotes.text.toString()

            if (TextUtils.isEmpty(judulNotes)) {
                binding.etJudul.error = "Masukkan Judul Notes"
            }
            if (TextUtils.isEmpty(isiNotes)) {
                binding.etIsiNotes.error = "Masukkan Isi Notes"
            }

            val notes = HashMap<String, String>()
            notes["title"] = judulNotes
            notes["body"] = isiNotes

            binding.btnTambahNotes.text = "Menyimpan...."

            val notesService: NotesServices = ServiceBuilder.buildService(NotesServices::class.java)
            val requestCall: Call<Responses> = notesService.addNotes(notes)

            requestCall.enqueue(object : retrofit2.Callback<Responses> {
                override fun onFailure(call: Call<Responses>, t: Throwable) {
                    Toast.makeText(this@TambahNotesActivity,
                        "Error terjadi ketika sedang menambahkan notes: ${t.toString()}",
                        Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<Responses>,
                    response: Response<Responses>
                ) {
                    if (!response.body()?.error!!) {
                        val responses: Responses? = response.body()!!
                        responses.let {
                            Toast.makeText(this@TambahNotesActivity,
                            response.body()?.message,
                            Toast.LENGTH_LONG).show()

                            val intent: Intent = Intent(this@TambahNotesActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    }
                }
            })
        }
    }

    override fun onBackPressed() {
        val judulNotes = binding.etJudul.text.toString()
        val isiNotes = binding.etIsiNotes.text.toString()

        if (!TextUtils.isEmpty(judulNotes) || !TextUtils.isEmpty(isiNotes)) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda ingin keluar dari halaman ini? \n Progress notes tidak akan disimpan.")

            builder.setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                finish()
            }

            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}