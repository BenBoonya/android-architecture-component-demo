package com.ben.boonya.architecturecomponentdemo.filmlist

import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.base.BaseRepository
import com.ben.boonya.architecturecomponentdemo.common.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.ben.boonya.architecturecomponentdemo.model.FilmList
import com.ben.boonya.architecturecomponentdemo.model.StarWarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
class FilmListRepository : BaseRepository() {

    fun getAllFilms(successHandler: (FilmList?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {
        makeRequest(apiService.getAllFilms(), successHandler, failureHandler)
    }
}