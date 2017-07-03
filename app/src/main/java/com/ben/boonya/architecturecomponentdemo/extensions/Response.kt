package com.ben.boonya.architecturecomponentdemo.extensions

import android.text.TextUtils
import com.ben.boonya.architecturecomponentdemo.common.model.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

/**
 * Created by oozou on 7/3/2017 AD.
 */

fun <T> Response<T>.getError(): ErrorResponse? {
    try {
        val errorBody = this.errorBody()?.string()
        if (!TextUtils.isEmpty(errorBody)) {
            return Gson().fromJson(errorBody, ErrorResponse::class.java)
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}
