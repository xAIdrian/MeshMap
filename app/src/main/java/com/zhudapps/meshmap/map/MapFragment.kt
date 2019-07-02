package com.zhudapps.meshmap.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.zhudapps.meshmap.R
import com.zhudapps.meshmap.base.BaseFragment
import com.zhudapps.meshmap.base.ViewModelProviderFactory
import com.zhudapps.meshmap.map.MapFragmentViewModel.Companion.MY_PERMISSION_FINE_LOCATION
import kotlinx.android.synthetic.main.map_fragment.*
import javax.inject.Inject


class MapFragment : BaseFragment<MapFragmentViewModel>(), OnMapReadyCallback, PermissionsListener {

    @Inject //initialization is the only thing needed here
    lateinit var mapBox: Mapbox

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MapFragmentViewModel
    private var map: MapboxMap? = null

    private val permissionsManager = PermissionsManager(this)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MapFragmentViewModel::class.java)
        if (::factory.isInitialized) {
            initListeners()
        }
    }

    private fun initListeners() {
        viewModel.mapPinsList.observe(this, Observer { list ->
            Log.e("temptag", list.toString())

            list.forEach {
                map?.addMarker(
                    MarkerOptions()
                        .setSnippet(it.description)
                        .position(
                            LatLng(
                                it.latitude.toDouble(),
                                it.longitude.toDouble()
                            )
                        )
                )
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMapPins()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun getViewModel(): MapFragmentViewModel {
        return viewModel
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        this.map = mapboxMap

        if (mapboxMap.markers.isNotEmpty()) {
            mapboxMap.clear()
        }

        mapboxMap.setStyle(Style.MAPBOX_STREETS) {
            // Map is set up and the style has loaded. Now you can add data or make other map adjustments
            enableLocationComponent(it)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        activity?.findViewById<View>(R.id.content)?.let {
            Snackbar.make(
                it,
                R.string.please_permission,
                Snackbar.LENGTH_INDEFINITE
            ).setAction(R.string.grant, ShowPermissionRequest()).show()
        }
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            map?.getStyle(Style.OnStyleLoaded {
                enableLocationComponent(it)
            })
        } else {
            Toast.makeText(activity, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show()
        }
    }


    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(activity)) {

            // Get an instance of the component
            val locationComponent = map?.locationComponent

            // Activate with options
            activity?.applicationContext?.let { LocationComponentActivationOptions.builder(it, loadedMapStyle).build() }
                ?.let { locationComponent?.activateLocationComponent(it) }

            // Enable to make component visible
            locationComponent?.isLocationComponentEnabled = true

            // Set the component's camera mode
            locationComponent?.cameraMode = CameraMode.TRACKING

            // Set the component's render mode
            locationComponent?.renderMode = RenderMode.COMPASS
        } else {
            permissionsManager.requestLocationPermissions(activity)
        }
    }

    inner class ShowPermissionRequest : View.OnClickListener {

        override fun onClick(v: View) {
            // Code to undo the user's last action
            ActivityCompat.requestPermissions(
                activity as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSION_FINE_LOCATION
            )
        }
    }
}


