package com.ben.boonya.architecturecomponentdemo.filmlist


import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.common.base.BaseViewModel
import com.ben.boonya.architecturecomponentdemo.common.model.Film
import com.ben.boonya.architecturecomponentdemo.common.model.FilmList

class FilmListViewModel(application: Application) : BaseViewModel(application), FilmListContract.MainViewModel {
    private val repository = FilmListRepository()

    val filmDetailNavigation = MutableLiveData<Film>()

    val filmResponse = MutableLiveData<FilmList>()

    override fun getAllFilms() {
        isLoading.value = true
        repository.getAllFilms(
                {
                    isLoading.value = false
                    filmResponse.value = it
                }
                , this::handleError)
    }

    override fun onFilmItemClicked(id: Int) {
        filmDetailNavigation.value = getFilmAt(id)
    }

    /**
     * Adapter Callback
     */

    fun getFilmAt(position: Int): Film? {
        return filmResponse.value?.films?.get(position)
    }

    fun getFilmSize(): Int {
        filmResponse.value?.films?.let {
            return it.size
        }
        return 0
    }
}
