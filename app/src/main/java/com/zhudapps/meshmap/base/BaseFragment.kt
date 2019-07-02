package com.zhudapps.meshmap.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment


/**
 * Created by adrian mohnacs on 2019-06-29
 */
abstract class BaseFragment<V : ViewModel> : DaggerFragment() {

    private var viewModel: V? = null

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
    }
}