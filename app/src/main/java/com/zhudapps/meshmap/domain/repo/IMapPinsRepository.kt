package com.zhudapps.meshmap.domain.repo

import com.zhudapps.meshmap.model.MapPin
import io.reactivex.Observable

/**
 * Created by adrian mohnacs on 2019-06-30
 */
interface IMapPinsRepository {
    fun getMapPins(): Observable<List<MapPin>>
    fun getPinsFromCache(): Observable<List<MapPin>>
    fun getPinsFromApi(): Observable<List<MapPin>>
    fun storePinsInCache(users: List<MapPin>)
}