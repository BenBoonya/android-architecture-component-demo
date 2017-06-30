package com.ben.boonya.architecturecomponentdemo.characterlist

import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.base.BaseRepository
import com.ben.boonya.architecturecomponentdemo.common.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.ben.boonya.architecturecomponentdemo.model.CharacterList
import com.ben.boonya.architecturecomponentdemo.model.FilmList
import com.ben.boonya.architecturecomponentdemo.model.StarWarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListRepository : BaseRepository() {

    fun getCharacter(page: Int, successHandler: (CharacterList?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {
        makeRequest(apiService.getCharacters(page), successHandler, failureHandler)

    }
}