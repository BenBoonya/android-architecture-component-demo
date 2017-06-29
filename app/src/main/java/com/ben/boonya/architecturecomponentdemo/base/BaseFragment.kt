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

        viewModel.throwable.observe(this, Observer {
            it?.let {
                showErrorMessage(it)
            }
        })
    }

    protected fun showErrorMessage(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }
}