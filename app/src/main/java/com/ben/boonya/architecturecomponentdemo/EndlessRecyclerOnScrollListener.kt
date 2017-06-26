package com.ben.boonya.architecturecomponentdemo

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager


/**
 * Created by oozou on 6/21/2017 AD.
 */

class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager, private val onLoadMore: (Int) -> Unit) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 5// The minimum amount of items to have below your current scroll position before loading more.
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            // End has been reached

            // Do something
            currentPage++

            onLoadMore(currentPage)

            loading = true
        }
    }

    fun resetState() {
        this.currentPage = 1
        this.previousTotal = 0
        this.loading = true
    }


    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}