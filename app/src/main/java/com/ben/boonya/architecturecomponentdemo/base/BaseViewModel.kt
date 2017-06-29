package com.ben.boonya.architecturecomponentdemo.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData

/**
 * Created by oozou on 6/28/2017 AD.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading = MediatorLiveData<Boolean>()
    val throwable = MediatorLiveData<Throwable>()
}