package com.ben.boonya.architecturecomponentdemo.filmlist

import com.ben.boonya.architecturecomponentdemo.model.Film


interface FilmListContract {
    interface MainView {
        fun showLoadingDialog(isLoading: Boolean)

        fun showMessage(message: String)

        fun showAllFilms(films: List<Film>)

        fun navigateToFilmPage(film: Film)
    }

    interface MainViewModel {
        fun getAllFilms()

        fun onFilmItemClicked(id: Int)
    }
}
