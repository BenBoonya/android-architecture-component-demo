package com.ben.boonya.architecturecomponentdemo.film

import com.ben.boonya.architecturecomponentdemo.common.base.BaseRepository
import com.ben.boonya.architecturecomponentdemo.common.model.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.common.model.Film


/**
 * Created by oozou on 6/19/2017 AD.
 */
class FilmRepository : BaseRepository() {

    fun getFilmById(id: Long, successHandler: (Film?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {
        makeRequest(apiService.getFilm(id), successHandler, failureHandler)

    }
}

