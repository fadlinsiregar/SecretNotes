package com.example.secretnotes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Notes(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("secret_key")
    var secretKey: String? = null,
): Serializable
