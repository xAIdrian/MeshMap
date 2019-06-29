package com.zhudapps.meshmap.daggerdi.activity

import com.zhudapps.meshmap.map.MainNavigationContract
import dagger.Module
import dagger.Provides
import com.zhudapps.meshmap.map.MainActivity



@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainNavigationContract {
        return mainActivity
    }

//    @Provides
//    fun provideMainPresenter(mainView: MainView, apiService: ApiService): MainPresenter {
//        return MainPresenterImpl(mainView, apiService)
//    }

}
