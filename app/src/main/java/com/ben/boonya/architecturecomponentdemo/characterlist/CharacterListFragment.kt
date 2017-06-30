package com.ben.boonya.architecturecomponentdemo.characterlist

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.boonya.architecturecomponentdemo.R
import com.ben.boonya.architecturecomponentdemo.base.BaseFragment
import com.ben.boonya.architecturecomponentdemo.extensions.onLoadMoreListener
import com.ben.boonya.architecturecomponentdemo.extensions.resetLoadMoreState
import kotlinx.android.synthetic.main.fragment_character_list.*

/**
 * Created by oozou on 6/21/2017 AD.
 */
class CharacterListFragment : BaseFragment<CharacterListViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    override val viewModelClass = CharacterListViewModel::class.java
    private lateinit var characterListAdapter: CharacterListAdapter
    private val registry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharacterByPage(1)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary)

        characterListAdapter = CharacterListAdapter(viewModel)
        rvCharacter.adapter = characterListAdapter
        rvCharacter.layoutManager = LinearLayoutManager(activity)
        rvCharacter.onLoadMoreListener(startPage = 1) {
            currentPage ->
            viewModel.getCharacterByPage(currentPage)
        }

        attachObserver()
    }

    fun attachObserver() {
        viewModel.characterResponse.observe(this, Observer {
            it?.let {
                characterListAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun getLifecycle(): LifecycleRegistry = registry


    override fun onRefresh() {
        rvCharacter.resetLoadMoreState()
        viewModel.clearCharacterList()
        viewModel.getCharacterByPage(1)
        viewModel.nextPage = null
    }

    override fun showLoadingView(isLoading: Boolean) {
        if (!isLoading) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    companion object {
        fun newInstance(): CharacterListFragment {
            return CharacterListFragment()
        }
    }
}