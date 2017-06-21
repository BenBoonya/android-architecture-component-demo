package com.ben.boonya.architecturecomponentdemo.filmlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.boonya.architecturecomponentdemo.R

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
class FilmListAdapter(var filmListViewModel: FilmListViewModel) : RecyclerView.Adapter<FilmHolder>() {

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.title.text = filmListViewModel.getFilmAt(position)?.title
        holder.setItemClickListener(View.OnClickListener {
            filmListViewModel.onFilmItemClicked(position)
        })
    }

    override fun getItemCount(): Int {
        return filmListViewModel.getFilmSize()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmHolder(view)
    }
}