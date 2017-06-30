package com.ben.boonya.architecturecomponentdemo.film

import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.base.BaseRepository
import com.ben.boonya.architecturecomponentdemo.common.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.ben.boonya.architecturecomponentdemo.model.CharacterList
import com.ben.boonya.architecturecomponentdemo.model.Film
import com.ben.boonya.architecturecomponentdemo.model.StarWarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by oozou on 6/19/2017 AD.
 */
class FilmRepository : BaseRepository() {

    fun getFilmById(id: Long, successHandler: (Film?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {
        makeRequest(apiService.getFilm(id), successHandler, failureHandler)

    }
}

