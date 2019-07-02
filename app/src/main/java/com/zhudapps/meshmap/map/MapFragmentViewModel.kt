package com.zhudapps.meshmap.map

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.mapboxsdk.style.sources.Source
import com.zhudapps.meshmap.domain.DataManager
import com.zhudapps.meshmap.model.MapPin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapFragmentViewModel @Inject constructor(
    val manager: DataManager
) : ViewModel() {

    companion object {
        const val MY_PERMISSION_FINE_LOCATION = 98
    }

    val mapPinsList = MutableLiveData<List<MapPin>>()

    @SuppressLint("CheckResult")
    fun getMapPins() {
        manager.getMapPins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { mapPinsList.value = it }, { Log.e("grrrrr", it.message) })
    }
}
