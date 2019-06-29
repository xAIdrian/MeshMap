package com.zhudapps.meshmap.daggerdi

import android.app.Application
import android.content.Context
import com.zhudapps.meshmap.daggerdi.activity.MainActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module(subcomponents = [ MainActivityComponent::class ])
class AppModule {

    @Provides
    @Singleton
    fun provideContent(app: Application): Context {
        return app
    }
}