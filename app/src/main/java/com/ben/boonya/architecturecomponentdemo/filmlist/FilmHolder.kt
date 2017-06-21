package com.ben.boonya.architecturecomponentdemo.filmlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ben.boonya.architecturecomponentdemo.R

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
class FilmHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title by lazy { itemView.findViewById(R.id.tvTitle) as TextView }

    fun setItemClickListener(onFilmClickListener: View.OnClickListener) {
        itemView.setOnClickListener(onFilmClickListener)
    }
}
