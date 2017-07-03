package com.ben.boonya.architecturecomponentdemo.common.base

import com.ben.boonya.architecturecomponentdemo.common.model.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.common.api.Apis
import com.ben.boonya.architecturecomponentdemo.extensions.getError
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
                        false -> failureHandler(response.getError(), response.code())
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
}