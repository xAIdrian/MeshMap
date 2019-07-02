package com.zhudapps.meshmap.daggerdi

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.zhudapps.meshmap.domain.api.ApiServiceGenerator
import com.zhudapps.meshmap.domain.api.TennaClient
import com.zhudapps.meshmap.domain.repo.MapPinsRepository
import com.zhudapps.meshmap.domain.room.MapPinDao
import com.zhudapps.meshmap.domain.room.MeshMapDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by adrian mohnacs on 2019-07-01
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun providesProductDao(context: Context): MapPinDao {
        return Room.databaseBuilder(
            context,
            MeshMapDatabase::class.java,
            context.getString(com.zhudapps.meshmap.R.string.room_database)
        ).build().mapPinDao()
    }

    @Singleton
    @Provides
    internal fun productRepository(productDao: MapPinDao, api: TennaClient): MapPinsRepository {
        return MapPinsRepository(productDao, api)
    }

    @Provides
    @Singleton
    fun providesTennaClient(): TennaClient {
        return ApiServiceGenerator.createService(TennaClient::class.java)
    }
}