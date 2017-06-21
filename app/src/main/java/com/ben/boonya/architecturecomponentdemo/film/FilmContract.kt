package com.ben.boonya.architecturecomponentdemo.film

interface FilmContract {
    interface FilmView {
        fun showLoadingDialog(isLoading: Boolean)

        fun showMessage(message: String)

        fun showTitle(title: String)

        fun showReleaseDate(dateString: String)

        fun showDirector(director: String)

        fun showCrawl(crawl: String)
    }

    interface FilmPresenter {
        fun getFilm(id: Long)
    }
}
