package com.ben.boonya.architecturecomponentdemo.filmlist

import com.ben.boonya.architecturecomponentdemo.common.base.BaseRepository
import com.ben.boonya.architecturecomponentdemo.common.model.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.common.model.FilmList

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
class FilmListRepository : BaseRepository() {

    fun getAllFilms(successHandler: (FilmList?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {
        makeRequest(apiService.getAllFilms(), successHandler, failureHandler)
    }
}