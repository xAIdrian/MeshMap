package com.zhudapps.meshmap.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhudapps.meshmap.domain.DataManager
import com.zhudapps.meshmap.model.MapPin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapFragmentViewModel @Inject constructor(
    val manager: DataManager
) : ViewModel() {

    companion object {
        val TAG = MapFragmentViewModel::class.simpleName
    }

    val mapPinsList = MutableLiveData<List<MapPin>>()

    fun getMapPins() {
        manager.getMapPins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ mapPinsList.value = it }, { Log.e(TAG, it.message) })
    }
}
