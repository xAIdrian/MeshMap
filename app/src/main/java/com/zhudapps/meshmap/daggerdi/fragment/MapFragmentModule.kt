package com.zhudapps.meshmap.daggerdi.fragment

import android.content.Context
import com.mapbox.mapboxsdk.Mapbox
import com.zhudapps.meshmap.BuildConfig
import dagger.Module
import dagger.Provides

/**
 * Created by adrian mohnacs on 2019-06-29
 */
@Module
class MapFragmentModule {

    @Provides
    fun provideMapBox(context: Context): Mapbox {
        return Mapbox.getInstance(context, BuildConfig.apikey)
    }
}