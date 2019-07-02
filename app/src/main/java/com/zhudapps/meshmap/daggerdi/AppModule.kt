package com.zhudapps.meshmap.daggerdi

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhudapps.meshmap.R
import com.zhudapps.meshmap.domain.api.ApiServiceGenerator
import com.zhudapps.meshmap.domain.api.TennaClient
import com.zhudapps.meshmap.domain.room.MeshMapDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideRoom(context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            MeshMapDatabase::class.java,
            context.getString(R.string.room_database)
        ).build()
    }
}