package com.zhudapps.meshmap

import javax.inject.Inject
import android.os.Looper.getMainLooper
import com.mapbox.android.core.location.LocationEngineRequest
import com.mapbox.android.core.location.LocationEngineProvider
import android.annotation.SuppressLint
import android.content.Context


/**
 * Created by adrian mohnacs on 2019-07-01
 */
class LocationEngine @Inject constructor(
    val context: Context) {

    companion object {
        const val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
        const val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5
    }

    // Variables needed to add the location engine
    private var locationEngine = LocationEngineProvider.getBestLocationEngine(context)

    /**
     * Set up the LocationEngine and the parameters for querying the device's location
     */
    @SuppressLint("MissingPermission")
    private fun initLocationEngine() {

        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()

//        locationEngine.requestLocationUpdates(request, callback, getMainLooper())
//        locationEngine.getLastLocation(callback)
    }

}