package com.ben.boonya.architecturecomponentdemo.film

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.boonya.architecturecomponentdemo.R
import com.ben.boonya.architecturecomponentdemo.base.BaseFragment
import com.ben.boonya.architecturecomponentdemo.model.Film
import kotlinx.android.synthetic.main.fragment_film_detail.*

/**
 * Created by oozou on 6/29/2017 AD.
 */
class FilmDetailFragment : BaseFragment<FilmViewModel>(), FilmContract.FilmView {

    override val viewModelClass = FilmViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFilm(getFilmId())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_film_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        attachObserver()
    }

    fun attachObserver() {
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let {
                showLoadingDialog(it)
            }
        })

        viewModel.filmResponse.observe(this, Observer<Film> {
            it?.let {
                showTitle(it.title)
                showReleaseDate(it.releaseDate)
                showDirector(it.director)
                showDirector(it.openingCrawl)
            }
        })
    }

    override fun showLoadingDialog(isLoading: Boolean) {
        if (isLoading) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }

    override fun showTitle(title: String) {
        activity.title = title
    }

    override fun showReleaseDate(dateString: String) {
        tvReleaseDate.text = dateString
    }

    override fun showDirector(director: String) {
        tvDirector.text = director
    }

    override fun showCrawl(crawl: String) {
        tvCrawl.text = crawl
    }

    companion object {
        fun newInstance(): FilmDetailFragment {
            return FilmDetailFragment()
        }
    }

}