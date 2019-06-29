package com.zhudapps.meshmap.dagger

import android.app.Application
import android.content.Context
import com.zhudapps.meshmap.dagger.activity.MainActivityComponent
import com.zhudapps.meshmap.dagger.fragment.MapFragmentComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module(subcomponents = [
    MainActivityComponent::class,
    MapFragmentComponent::class
])
class AppModule {

    @Provides
    @Singleton
    fun provideContent(app: Application): Context {
        return app
    }

}