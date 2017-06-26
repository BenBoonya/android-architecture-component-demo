package com.ben.boonya.architecturecomponentdemo.extensions

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlin.reflect.KProperty

/**
 * Created by jutikornvarojananulux on 6/25/2017 AD.
 */
val RecyclerView.visibleThreshold : Int get() = 5

val LinearLayoutManager.firstVisibleItem : Int get() = findFirstVisibleItemPosition()


var RecyclerView.previousTotal: Int by NumberDelegate()
var RecyclerView.currentPage: Int by NumberDelegate()
var RecyclerView.loading: Boolean by BooleanDelegate()


fun RecyclerView.onLoadMoreListener(startPage: Int = 1, onLoadMore: (Int) -> Unit) {
    this.currentPage = startPage
    this.previousTotal = 0
    this.loading = true
    this.layoutManager?.let {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItem = (layoutManager as LinearLayoutManager).firstVisibleItem

                if (loading && layoutManager.itemCount > previousTotal) {
                    loading = false
                    previousTotal = layoutManager.itemCount
                }

                if (!loading && (layoutManager.itemCount - childCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
                    // Do something
                    currentPage++

                    onLoadMore(currentPage)

                    loading = true
                }
            }

        })
    }
}

fun RecyclerView.resetLoadMoreState(startPage: Int = 1) {
    this.currentPage = startPage
    this.previousTotal = 0
    this.loading = true
}

class NumberDelegate {

    var number: Int? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return number?:0
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        number = value
    }
}

class BooleanDelegate {

    var myBoolean: Boolean? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return myBoolean?:true
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        myBoolean = value
    }
}