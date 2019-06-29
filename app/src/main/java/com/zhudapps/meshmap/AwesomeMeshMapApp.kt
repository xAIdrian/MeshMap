package com.zhudapps.meshmap

import android.app.Activity
import android.app.Application
import com.zhudapps.meshmap.dagger.DaggerAppComponent
import dagger.android.HasActivityInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector



/**
 * Created by adrian mohnacs on 2019-06-28
 */
class AwesomeMeshMapApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        //use android networking if we are having a hard time AndroidNetworking.initialize(getApplicationContext());
    }
}