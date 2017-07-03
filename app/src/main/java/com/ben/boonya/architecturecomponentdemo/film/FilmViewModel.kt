package com.ben.boonya.architecturecomponentdemo.film

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.common.base.BaseViewModel
import com.ben.boonya.architecturecomponentdemo.common.model.Film

class FilmViewModel(application: Application) : BaseViewModel(application), FilmContract.FilmPresenter {

    private val repository = FilmRepository()

    val filmResponse = MutableLiveData<Film>()

    override fun getFilm(id: Long) {
        isLoading.value = true
        repository.getFilmById(id,
                {
                    filmResponse.value = it
                    isLoading.value = false
                },
                this::handleError)
    }
}