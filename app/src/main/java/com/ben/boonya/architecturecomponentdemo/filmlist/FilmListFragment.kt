package com.ben.boonya.architecturecomponentdemo.filmlist

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ben.boonya.architecturecomponentdemo.R
import com.ben.boonya.architecturecomponentdemo.model.Film
import kotlinx.android.synthetic.main.fragment_film_list.*

class FilmListFragment : Fragment(), FilmListContract.MainView, LifecycleRegistryOwner {

    private lateinit var filmListAdapter: FilmListAdapter
    private lateinit var viewmodel: FilmListViewModel
    private val registry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = createViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        filmListAdapter = FilmListAdapter(viewmodel)
        rvFilms.layoutManager = LinearLayoutManager(activity)
        rvFilms.adapter = filmListAdapter
        viewmodel.getAllFilms()
        attachObserver()
    }

    fun createViewModel(): FilmListViewModel {
        return ViewModelProviders.of(this).get(FilmListViewModel::class.java)
    }

    fun attachObserver() {
        viewmodel.isLoading.observe(this, Observer<Boolean> {
            it?.let {
                showLoadingDialog(it)
            }
        })

        viewmodel.filmResponse.observe(this, Observer {
            it?.let {
                filmListAdapter.notifyDataSetChanged()
            }
        })

        viewmodel.throwable.observe(this, Observer {
            it?.message?.let {
                showMessage(it)
            }
        })

        viewmodel.filmDetailNavigation.observe(this, Observer {
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

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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
