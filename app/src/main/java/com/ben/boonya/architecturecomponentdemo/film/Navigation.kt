package com.ben.boonya.architecturecomponentdemo.film

import com.ben.boonya.architecturecomponentdemo.Constants

/**
 * Created by oozou on 6/20/2017 AD.
 */
fun FilmDetailActivity.getFilmId(): Long {
    return intent.getLongExtra(Constants.EXTRA_FILM_ID, 0)
}