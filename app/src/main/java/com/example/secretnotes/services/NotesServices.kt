package com.example.secretnotes.services

import com.example.secretnotes.models.Notes
import com.example.secretnotes.models.Responses
import retrofit2.Call
import retrofit2.http.*

interface NotesServices {
//    @GET("notes/{id}")

    @POST("notes")
    fun addNotes(
        @Body newNotes: Notes
    ): Call<Responses>

    @PUT("notes/{secret_key}")
    fun updateNotes(
        @Body updatedNotes: Notes,
        @Path("secret_key") secretKey: String
    ): Call<Responses>

    @DELETE("notes/{secret_key}")
    fun deleteNotes(
        @Path("secret_key") secretKey: String
    ): Call<Responses>
}