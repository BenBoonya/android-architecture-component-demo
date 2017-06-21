package com.ben.boonya.architecturecomponentdemo.filmlist

import android.content.Intent
import com.ben.boonya.architecturecomponentdemo.Constants
import com.ben.boonya.architecturecomponentdemo.film.FilmDetailActivity
import com.ben.boonya.architecturecomponentdemo.model.Film

/**
 * Created by oozou on 6/19/2017 AD.
 */

fun FilmListFragment.openFilm(film: Film) {
    val intent = Intent(this.activity, FilmDetailActivity::class.java)
    intent.putExtra(Constants.EXTRA_FILM_ID, film.episodeId)
    this.startActivity(intent)
}