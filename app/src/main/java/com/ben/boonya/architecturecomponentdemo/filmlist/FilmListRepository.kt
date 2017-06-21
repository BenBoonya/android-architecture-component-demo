package com.ben.boonya.architecturecomponentdemo.filmlist

import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.ben.boonya.architecturecomponentdemo.model.FilmList
import com.ben.boonya.architecturecomponentdemo.model.StarWarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
class FilmListRepository {

    val filmListResponse = MutableLiveData<Pair<FilmList?, Throwable?>>()

    private val starWarsApi: StarWarsApi = Apis.getStarWarApi()
    fun getAllFilms() {
        starWarsApi.getAllFilms().enqueue(object : Callback<FilmList?> {
            override fun onResponse(call: Call<FilmList?>, response: Response<FilmList?>) {
                filmListResponse.value = Pair(response.body(), null)
            }

            override fun onFailure(call: Call<FilmList?>, t: Throwable) {
                filmListResponse.value = Pair(null, t)
            }
        })
    }


}