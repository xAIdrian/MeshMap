package com.zhudapps.meshmap.dagger.activity

import dagger.Module
import android.app.Activity
import dagger.android.AndroidInjector
import com.zhudapps.meshmap.map.MainActivity
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds


/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}