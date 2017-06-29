package com.ben.boonya.architecturecomponentdemo.filmlist


import android.app.Application
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.base.BaseViewModel
import com.ben.boonya.architecturecomponentdemo.model.Film
import com.ben.boonya.architecturecomponentdemo.model.FilmList

class FilmListViewModel(application: Application) : BaseViewModel(application), FilmListContract.MainViewModel {
    private val repository = FilmListRepository()

    val filmDetailNavigation = MutableLiveData<Film>()

    init {
        isLoading.addSource(repository.filmListResponse) {
            isLoading.value = false
        }
        throwable.addSource(repository.filmListResponse) {
            it?.second?.let {
                throwable.value = it
            }
        }
    }

    val filmResponse = MediatorLiveData<FilmList>()

    init {
        filmResponse.addSource(repository.filmListResponse)
        {
            it?.first?.let {
                filmResponse.value = it
            }

        }
    }

    override fun getAllFilms() {
        isLoading.value = true
        repository.getAllFilms()
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
