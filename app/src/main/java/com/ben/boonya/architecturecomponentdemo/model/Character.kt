package com.ben.boonya.architecturecomponentdemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oozou on 6/21/2017 AD.
 */
data class Character(
        val name: String,
        val height: String,
        val mass: String,
        @SerializedName("hair_color") val hairColor: String,
        @SerializedName("skin_color") val skinColor: String)
