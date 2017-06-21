package com.ben.boonya.architecturecomponentdemo.film

import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.ben.boonya.architecturecomponentdemo.model.Film
import com.ben.boonya.architecturecomponentdemo.model.StarWarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by oozou on 6/19/2017 AD.
 */
class FilmRepository {

    val filmResponse = MutableLiveData<Pair<Film?, Throwable?>>()

    private val starWarsApi: StarWarsApi = Apis.getStarWarApi()
    fun getFilmById(id: Long) {
        starWarsApi.getFilm(id).enqueue(object : Callback<Film?> {
            override fun onResponse(call: Call<Film?>?, response: Response<Film?>) {
                filmResponse.value = Pair(response.body(), null)
            }

            override fun onFailure(call: Call<Film?>?, t: Throwable?) {
                filmResponse.value = Pair(null, t)
            }
        })

    }
}

