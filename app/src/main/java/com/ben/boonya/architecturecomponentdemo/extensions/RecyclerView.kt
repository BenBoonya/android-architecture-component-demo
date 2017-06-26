package com.ben.boonya.architecturecomponentdemo.extensions

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import junit.framework.Assert

/**
 * Created by jutikornvarojananulux on 6/25/2017 AD.
 */
fun RecyclerView.onLoadMoreListener(onLoadMore: (Int) -> Unit) {
    layoutManager?.let {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var previousTotal = 0
            var loading = true
            var visibleThreshold = 5
            var firstVisibleItem = 0
            var visibleItemCount = 0
            var totalItemCount = 0
            var currentPage = 1

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = childCount
                totalItemCount = layoutManager.itemCount
                firstVisibleItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (loading && totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }

                if (!loading && (totalItemCount - visibleItemCount)
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