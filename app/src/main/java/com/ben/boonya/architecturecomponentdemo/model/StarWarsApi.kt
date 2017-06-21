package com.ben.boonya.architecturecomponentdemo.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
interface StarWarsApi {
    @GET("films/")
    fun getAllFilms(): Call<FilmList>

    @GET("films/{id}/")
    fun getFilm(@Path("id") id: Long): Call<Film>

    @GET("people/")
    fun getCharacters(@Query("page") page: Int): Call<CharacterList>
}
