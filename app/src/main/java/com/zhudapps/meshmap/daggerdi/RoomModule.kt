package com.zhudapps.meshmap.daggerdi

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhudapps.meshmap.R
import com.zhudapps.meshmap.domain.room.MeshMapDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-28
 */
@Module
class RoomModule {

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