package com.zhudapps.meshmap.dagger.activity

import com.zhudapps.meshmap.map.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Subcomponent(modules = [ MainActivityModule::class ])
interface MainActivityComponent: AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity> ()
}