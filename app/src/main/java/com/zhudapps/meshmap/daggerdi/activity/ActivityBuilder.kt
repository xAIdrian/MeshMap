package com.zhudapps.meshmap.daggerdi.activity

import com.zhudapps.meshmap.daggerdi.fragment.FragmentBuilder
import com.zhudapps.meshmap.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    internal abstract fun bindMainActivity(): MainActivity
}