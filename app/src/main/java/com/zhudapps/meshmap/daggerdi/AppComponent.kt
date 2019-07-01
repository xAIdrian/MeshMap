package com.zhudapps.meshmap.daggerdi

import android.app.Application
import com.zhudapps.meshmap.MeshMapApp
import com.zhudapps.meshmap.daggerdi.activity.ActivityBuilder
import com.zhudapps.meshmap.daggerdi.fragment.FragmentBuilder
import com.zhudapps.meshmap.daggerdi.fragment.MapFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MeshMapApp)
}