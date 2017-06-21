package com.ben.boonya.architecturecomponentdemo.characterlist

import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.model.Apis
import com.ben.boonya.architecturecomponentdemo.model.CharacterList
import com.ben.boonya.architecturecomponentdemo.model.StarWarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListRepository {
    val characterListResponse = MutableLiveData<Pair<CharacterList?, Throwable?>>()
    private val starWarsApi: StarWarsApi = Apis.getStarWarApi()

    fun getCharacter(page: Int) {
        starWarsApi.getCharacters(page).enqueue(object : Callback<CharacterList> {
            override fun onFailure(call: Call<CharacterList>?, t: Throwable?) {
                characterListResponse.value = Pair(null, t)
            }

            override fun onResponse(call: Call<CharacterList>?, response: Response<CharacterList>?) {
                characterListResponse.value = Pair(response?.body(), null)
            }

        })
    }
}