package com.zhudapps.meshmap.dagger.activity

import com.zhudapps.meshmap.MainNavigation
import dagger.Module
import dagger.Provides
import com.zhudapps.meshmap.map.MainActivity



@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainNavigation {
        return mainActivity
    }

//    @Provides
//    fun provideMainPresenter(mainView: MainView, apiService: ApiService): MainPresenter {
//        return MainPresenterImpl(mainView, apiService)
//    }

}
