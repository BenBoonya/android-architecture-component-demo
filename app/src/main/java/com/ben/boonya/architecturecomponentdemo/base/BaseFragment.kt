package com.ben.boonya.architecturecomponentdemo.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by oozou on 6/28/2017 AD.
 */
@Suppress("LeakingThis")
abstract class BaseFragment<T : BaseViewModel> : Fragment(), LifecycleRegistryOwner {

    abstract val viewModelClass: Class<T>

    protected lateinit var viewModel: T

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        attachObserver()
    }

    private fun attachObserver() {
        viewModel.apiError.observe(this, Observer {
            it?.first?.let {
                showErrorMessage(it.toString())
                return@Observer
            }
            it?.second?.let {
                showErrorMessage()
                return@Observer
            }
            handleNoConnection()
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let {
                showLoadingView(it)
            }
        })
    }

    protected fun showErrorMessage(message: String = "Something went wrong please try again later") {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun handleNoConnection() {
        Toast.makeText(activity, "No connection", Toast.LENGTH_SHORT).show()
    }

    abstract fun showLoadingView(isLoading: Boolean)
}