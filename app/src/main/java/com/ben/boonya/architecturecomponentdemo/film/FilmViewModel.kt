package com.ben.boonya.architecturecomponentdemo.film

import android.app.Application
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ben.boonya.architecturecomponentdemo.base.BaseViewModel
import com.ben.boonya.architecturecomponentdemo.model.Film

class FilmViewModel(application: Application) : BaseViewModel(application), FilmContract.FilmPresenter {

    private val repository = FilmRepository()

    init {
        isLoading.addSource(repository.filmResponse) {
            isLoading.value = false
        }

        throwable.addSource(repository.filmResponse)
        {
            it?.let {
                throwable.value = it.second
            }
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

    override fun getFilm(id: Long) {
        isLoading.value = true
        repository.getFilmById(id)
    }
}