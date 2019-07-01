package com.zhudapps.meshmap.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhudapps.meshmap.model.MapPin
import io.reactivex.Single

/**
 * Created by adrian mohnacs on 2019-06-30
 */
@Dao
interface MapPinDao {

    @Query("SELECT * FROM pins")
    fun getPins(): Single<List<MapPin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pins: MapPin)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pins: List<MapPin>)
}