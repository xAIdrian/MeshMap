package com.zhudapps.meshmap.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhudapps.meshmap.domain.DataManager
import com.zhudapps.meshmap.map.MapFragmentViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-07-01
 */
@Singleton
class ViewModelProviderFactory @Inject
constructor(private val dataManager: DataManager) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MapFragmentViewModel::class.java) -> MapFragmentViewModel(dataManager) as T
            else -> throw IllegalArgumentException("ViewModelProviderFactory Unknown ViewModel class: " + modelClass.name)
        }
    }
}