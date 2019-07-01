package com.zhudapps.meshmap.domain

import com.zhudapps.meshmap.model.MapPin
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by adrian mohnacs on 2019-06-30
 */
interface IDataManager {
    fun getMapPins(): Observable<List<MapPin>>
}