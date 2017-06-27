package com.ben.boonya.architecturecomponentdemo.characterlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ben.boonya.architecturecomponentdemo.ProgressViewHolder
import com.ben.boonya.architecturecomponentdemo.R

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListAdapter(var viewmodel: CharacterListViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterHolder) {
            holder.title.text = viewmodel.getCharacterAt(position)?.name
        }
    }

    override fun getItemCount(): Int {
        if (viewmodel.nextPage != null) {
            return viewmodel.getCharacterSize() + 1
        } else {
            return viewmodel.getCharacterSize()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_ITEM) {
            return CharacterHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
        } else {
            return ProgressViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position >= viewmodel.getCharacterSize()) return VIEW_PROG else return VIEW_ITEM
    }
}