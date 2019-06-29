package com.zhudapps.meshmap.daggerdi

import android.app.Application
import com.zhudapps.meshmap.AwesomeMeshMapApp
import com.zhudapps.meshmap.daggerdi.activity.ActivityBuilder
import com.zhudapps.meshmap.daggerdi.fragment.FragmentBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
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

    fun inject(app: AwesomeMeshMapApp)
}