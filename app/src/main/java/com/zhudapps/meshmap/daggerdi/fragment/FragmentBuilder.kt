package com.zhudapps.meshmap.daggerdi.fragment

import com.zhudapps.meshmap.map.MapFragment
import com.zhudapps.meshmap.map.MapListDialogFragment
import com.zhudapps.meshmap.maplist.MapListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [ MapFragmentModule::class ])
    internal abstract fun provideMapFragment(): MapFragment

    @ContributesAndroidInjector
    internal abstract fun provideMapListFragment(): MapListFragment
}