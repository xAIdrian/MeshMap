package com.zhudapps.meshmap.map

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.zhudapps.meshmap.R
import com.zhudapps.meshmap.base.BaseFragment
import com.zhudapps.meshmap.base.ViewModelProviderFactory
import com.zhudapps.meshmap.map.MapFragmentViewModel.Companion.MY_PERMISSION_FINE_LOCATION
import kotlinx.android.synthetic.main.map_fragment.*
import javax.inject.Inject




class MapFragment : BaseFragment<MapFragmentViewModel>(), OnMapReadyCallback {

    @Inject //initialization is the only thing needed here
    lateinit var mapBox: Mapbox

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MapFragmentViewModel
    private var map: MapboxMap? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MapFragmentViewModel::class.java)
        if (::factory.isInitialized) {
            initListeners()

            activity?.let {
                if (ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(it,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                        //user has already denied the permission
                        Snackbar.make(
                            it.findViewById(android.R.id.content),
                            R.string.please_permission,
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction(R.string.grant, ShowPermissionRequest()).show()

                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(it,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            MY_PERMISSION_FINE_LOCATION)

                        // ACCESS_FINE_LOCATION is an app-defined int constant.
                        // The callback method gets the result of the request.
                    }
                } else {
                    // Permission has already been granted
                }
            }
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
            Log.e("temptag", "map loaded")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            MY_PERMISSION_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    inner class ShowPermissionRequest : View.OnClickListener {

        override fun onClick(v: View) {
            // Code to undo the user's last action
            ActivityCompat.requestPermissions(
                activity as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSION_FINE_LOCATION)
        }
    }
}


