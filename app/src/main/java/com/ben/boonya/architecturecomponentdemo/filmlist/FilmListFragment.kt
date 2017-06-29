package com.ben.boonya.architecturecomponentdemo.filmlist

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.boonya.architecturecomponentdemo.R
import com.ben.boonya.architecturecomponentdemo.base.BaseFragment
import com.ben.boonya.architecturecomponentdemo.model.Film
import kotlinx.android.synthetic.main.fragment_film_list.*

class FilmListFragment : BaseFragment<FilmListViewModel>(), FilmListContract.MainView {

    override val viewModelClass = FilmListViewModel::class.java
    private lateinit var filmListAdapter: FilmListAdapter
    private val registry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllFilms()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        filmListAdapter = FilmListAdapter(viewModel)
        rvFilms.layoutManager = LinearLayoutManager(activity)
        rvFilms.adapter = filmListAdapter
        attachObserver()
    }

    fun attachObserver() {
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let {
                showLoadingDialog(it)
            }
        })

        viewModel.filmResponse.observe(this, Observer {
            it?.let {
                filmListAdapter.notifyDataSetChanged()
            }
        })

        viewModel.filmDetailNavigation.observe(this, Observer {
            it?.let {
                navigateToFilmPage(it)
            }
        })
    }

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun showLoadingDialog(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }

    override fun showAllFilms(films: List<Film>) {
        filmListAdapter.notifyDataSetChanged()
    }

    override fun navigateToFilmPage(film: Film) {
        openFilm(film)
    }

    companion object {
        fun newInstance(): FilmListFragment {
            return FilmListFragment()
        }
    }
}
