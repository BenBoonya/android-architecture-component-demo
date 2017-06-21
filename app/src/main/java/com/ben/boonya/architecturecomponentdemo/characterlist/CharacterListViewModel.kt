package com.ben.boonya.architecturecomponentdemo.characterlist

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.ben.boonya.architecturecomponentdemo.model.Character

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListViewModel : ViewModel() {
    private val repository = CharacterListRepository()
    var characterList: List<Character> = ArrayList()

    val isLoading = MediatorLiveData<Boolean>()

    init {
        isLoading.addSource(repository.characterListResponse) {
            isLoading.value = false
        }
    }

    val characterResponse = MediatorLiveData<List<Character>>()

    init {
        characterResponse.addSource(repository.characterListResponse)
        {
            it?.first?.characters?.let {
                characterList = characterList.plus(it)
                characterResponse.value = characterList
            }

        }
    }

    val throwable = MediatorLiveData<Throwable>()

    init {
        throwable.addSource(repository.characterListResponse)
        {
            it?.second?.let {
                throwable.value = it
            }
        }
    }

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
