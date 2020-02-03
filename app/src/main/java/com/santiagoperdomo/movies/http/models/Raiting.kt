package com.santiagoperdomo.movies.http.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Raiting {

    @SerializedName("Source")
    @Expose
    val source: String? = null
    @SerializedName("Value")
    @Expose
    val value: String? = null

}