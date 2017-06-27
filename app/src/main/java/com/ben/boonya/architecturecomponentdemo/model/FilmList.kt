package com.ben.boonya.architecturecomponentdemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oozou on 6/19/2017 AD.
 */
data class FilmList(@SerializedName("results") val films: List<Film>,
                    @SerializedName("next") val next: String?)