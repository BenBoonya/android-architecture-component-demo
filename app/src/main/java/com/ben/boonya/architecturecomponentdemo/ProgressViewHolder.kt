package com.ben.boonya.architecturecomponentdemo

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by oozou on 6/26/2017 AD.
 */
class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val progressBar by lazy { view.findViewById(R.id.progressBar) }
}