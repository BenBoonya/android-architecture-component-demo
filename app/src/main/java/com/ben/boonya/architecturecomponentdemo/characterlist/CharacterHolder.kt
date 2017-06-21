package com.ben.boonya.architecturecomponentdemo.characterlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ben.boonya.architecturecomponentdemo.R

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title by lazy { itemView.findViewById(R.id.title) as TextView }
}