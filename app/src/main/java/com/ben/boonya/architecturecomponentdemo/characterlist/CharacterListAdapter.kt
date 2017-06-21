package com.ben.boonya.architecturecomponentdemo.characterlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ben.boonya.architecturecomponentdemo.R

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListAdapter(var characterListViewModel: CharacterListViewModel) : RecyclerView.Adapter<CharacterHolder>() {

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.title.text = characterListViewModel.getCharacterAt(position)?.name
    }

    override fun getItemCount(): Int {
        return characterListViewModel.getCharacterSize()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterHolder(view)
    }
}