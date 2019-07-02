package com.zhudapps.meshmap.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.zhudapps.meshmap.R
import com.zhudapps.meshmap.base.BaseFragment
import com.zhudapps.meshmap.base.ViewModelProviderFactory
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
        initListeners()
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

        mapboxMap.setStyle(Style.MAPBOX_STREETS, object : Style.OnStyleLoaded {
            override fun onStyleLoaded(style: Style) {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                Log.e("temptag", "map loaded")
            }
        })
    }
}
