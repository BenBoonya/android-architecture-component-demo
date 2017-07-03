package com.ben.boonya.architecturecomponentdemo.filmlist

import com.ben.boonya.architecturecomponentdemo.common.model.Film


interface FilmListContract {
    interface MainView {
        fun showAllFilms(films: List<Film>)

        fun navigateToFilmPage(film: Film)
    }

    interface MainViewModel {
        fun getAllFilms()

        fun onFilmItemClicked(id: Int)
    }
}
