package com.ben.boonya.architecturecomponentdemo.base

import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.ben.boonya.architecturecomponentdemo.common.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by oozou on 6/29/2017 AD.
 */
open class BaseRepository {

    protected var apiService = Apis.getStarWarApi()

    protected fun <T> makeRequest(call: Call<T>, successHandler: (T?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {

        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                response?.let {
                    when (response.isSuccessful) {
                        true -> successHandler(it.body())
                        false -> failureHandler(getError(response), response.code())
                    }
                }
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                failureHandler(null, null)
                if (t is Exception) {
                    t.printStackTrace()
                }
            }
        })
    }

    protected fun <T> getError(response: Response<T>): ErrorResponse? {
        try {
            val errorBody = response.errorBody()?.string()
            if (!TextUtils.isEmpty(errorBody)) {
                return Gson().fromJson(errorBody, ErrorResponse::class.java)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
}