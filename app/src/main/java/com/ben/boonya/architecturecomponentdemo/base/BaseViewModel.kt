package com.ben.boonya.architecturecomponentdemo.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.common.ErrorResponse

/**
 * Created by oozou on 6/28/2017 AD.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading = MediatorLiveData<Boolean>()
    val apiError = MutableLiveData<Pair<ErrorResponse?, Int?>>()

    fun handleError(errorResponse: ErrorResponse?, code: Int?) {
        kotlin.run {
            isLoading.value = false
            apiError.value = Pair(errorResponse, code)
        }
    }
}