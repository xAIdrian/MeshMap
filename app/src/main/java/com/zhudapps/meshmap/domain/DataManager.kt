package com.zhudapps.meshmap.domain

import androidx.room.RoomDatabase
import javax.inject.Inject

/**
 * Created by adrian mohnacs on 2019-06-30
 */
class DataManager @Inject constructor(
    val appDatabase: RoomDatabase
): IDataManager {
}