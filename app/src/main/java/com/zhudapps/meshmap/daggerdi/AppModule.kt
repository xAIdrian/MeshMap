package com.zhudapps.meshmap.daggerdi

import android.app.Application
import android.content.Context
import com.zhudapps.meshmap.daggerdi.fragment.MapFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module(subcomponents = [ MapFragmentComponent::class ])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app
    }
}