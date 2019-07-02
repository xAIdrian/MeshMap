package com.zhudapps.meshmap.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhudapps.meshmap.model.MapPin

/**
 * Created by adrian mohnacs on 2019-06-30
 */
@Database(entities = arrayOf(MapPin::class), version = 1)
abstract class MeshMapDatabase: RoomDatabase() {
    abstract fun mapPinDao(): MapPinDao
}