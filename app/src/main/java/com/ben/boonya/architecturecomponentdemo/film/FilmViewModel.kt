package com.ben.boonya.architecturecomponentdemo.film

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ben.boonya.architecturecomponentdemo.model.Film

class FilmViewModel : ViewModel(), FilmContract.FilmPresenter {

    private val repository = FilmRepository()

    val isLoading = MediatorLiveData<Boolean>()

    init {
        isLoading.addSource(repository.filmResponse) {
            isLoading.value = false
        }
    }

    val filmResponse = MediatorLiveData<Film>()

    init {
        filmResponse.addSource(repository.filmResponse)
        {
            it?.let {
                filmResponse.value = it.first
            }
        }
    }

    val throwable = MediatorLiveData<Throwable>()

    init {
        throwable.addSource(repository.filmResponse)
        {
            it?.let {
                throwable.value = it.second
            }
        }
    }

    override fun getFilm(id: Long) {
        isLoading.value = true
        repository.getFilmById(id)
    }
}