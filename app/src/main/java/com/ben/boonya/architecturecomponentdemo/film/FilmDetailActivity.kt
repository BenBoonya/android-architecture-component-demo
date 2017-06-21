package com.ben.boonya.architecturecomponentdemo.film

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.ben.boonya.architecturecomponentdemo.R
import com.ben.boonya.architecturecomponentdemo.model.Film
import kotlinx.android.synthetic.main.activity_film_detail.*

class FilmDetailActivity : AppCompatActivity(), FilmContract.FilmView, LifecycleRegistryOwner {

    private val registry = LifecycleRegistry(this)
    private lateinit var viewModel: FilmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        viewModel = ViewModelProviders.of(this).get(FilmViewModel::class.java)
        viewModel.getFilm(getFilmId())
        attachObserver()
    }

    fun attachObserver() {
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let {
                showLoadingDialog(it)
            }
        })

        viewModel.throwable.observe(this, Observer<Throwable> {
            it?.message?.let {
                showMessage(it)
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

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun showLoadingDialog(isLoading: Boolean) {
        if (isLoading) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showTitle(title: String) {
        setTitle(title)
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
}
