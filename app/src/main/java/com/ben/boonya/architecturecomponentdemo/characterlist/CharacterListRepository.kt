package com.ben.boonya.architecturecomponentdemo.characterlist

import com.ben.boonya.architecturecomponentdemo.common.base.BaseRepository
import com.ben.boonya.architecturecomponentdemo.common.model.ErrorResponse
import com.ben.boonya.architecturecomponentdemo.common.model.CharacterList

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListRepository : BaseRepository() {

    fun getCharacter(page: Int, successHandler: (CharacterList?) -> Unit, failureHandler: (ErrorResponse?, Int?) -> Unit) {
        makeRequest(apiService.getCharacters(page), successHandler, failureHandler)

    }
}