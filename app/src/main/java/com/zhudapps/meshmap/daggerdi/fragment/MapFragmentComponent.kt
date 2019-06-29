package com.zhudapps.meshmap.daggerdi.fragment

import com.zhudapps.meshmap.map.MapFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by adrian mohnacs on 2019-06-29
 */
@Subcomponent(modules = [ MapFragmentModule::class ])
interface MapFragmentComponent: AndroidInjector<MapFragment> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MapFragment> ()
}