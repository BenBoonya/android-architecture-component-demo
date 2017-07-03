package com.ben.boonya.architecturecomponentdemo.characterlist

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.ben.boonya.architecturecomponentdemo.common.base.BaseViewModel
import com.ben.boonya.architecturecomponentdemo.common.model.Character

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListViewModel(application: Application) : BaseViewModel(application) {
    private val repository = CharacterListRepository()

    var nextPage: String? = null

    val characterResponse = MutableLiveData<List<Character>>()

    fun clearCharacterList() {
        characterResponse.value = ArrayList<Character>()
    }

    fun getCharacterByPage(page: Int) {
        isLoading.value = true
        repository.getCharacter(page,
                {
                    it?.characters?.let {
                        val characterList = characterResponse.value
                        characterResponse.value = characterList?.plus(it) ?: it
                    }
                    nextPage = it?.next
                    isLoading.value = false
                },
                this::handleError)
    }

    /**
     * Adapter callback
     */

    fun getCharacterSize(): Int {
        characterResponse.value?.let {
            return it.size
        }
        return 0
    }

    fun getCharacterAt(position: Int): Character? {
        return characterResponse.value?.get(position)
    }
}
