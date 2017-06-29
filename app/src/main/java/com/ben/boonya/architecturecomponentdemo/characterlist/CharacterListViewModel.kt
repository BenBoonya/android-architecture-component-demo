package com.ben.boonya.architecturecomponentdemo.characterlist

import android.app.Application
import android.arch.lifecycle.MediatorLiveData
import com.ben.boonya.architecturecomponentdemo.base.BaseViewModel
import com.ben.boonya.architecturecomponentdemo.model.Character

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListViewModel(application: Application) : BaseViewModel(application) {
    private val repository = CharacterListRepository()

    var nextPage: String? = null

    init {
        isLoading.addSource(repository.characterListResponse) {
            isLoading.value = false
        }

        throwable.addSource(repository.characterListResponse) {
            it?.second?.let {
                throwable.value = it
            }
        }
    }

    val characterResponse = MediatorLiveData<List<Character>>()

    init {
        characterResponse.addSource(repository.characterListResponse)
        {
            it?.first?.characters?.let {
                val characterList = characterResponse.value
                characterResponse.value = characterList?.plus(it) ?: it
            }
            nextPage = it?.first?.next
        }
    }

    fun clearCharacterList() {
        characterResponse.value = ArrayList<Character>()
    }

    /**
     * Adapter callback
     */

    fun getCharacterByPage(page: Int) {
        isLoading.value = true
        repository.getCharacter(page)
    }

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
