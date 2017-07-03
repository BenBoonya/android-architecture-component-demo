package com.ben.boonya.architecturecomponentdemo.common.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oozou on 6/21/2017 AD.
 */
data class CharacterList(@SerializedName("results") val characters: List<Character>,
                         @SerializedName("next") val next: String?)