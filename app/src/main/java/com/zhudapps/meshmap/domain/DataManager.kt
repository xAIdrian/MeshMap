package com.zhudapps.meshmap.domain

import com.zhudapps.meshmap.domain.repo.MapPinsRepository
import com.zhudapps.meshmap.model.MapPin
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by adrian mohnacs on 2019-06-30
 */
class DataManager @Inject constructor(
    private val mapPinsRepository: MapPinsRepository
): IDataManager {

    override fun getMapPins(): Observable<List<MapPin>> {
        return mapPinsRepository.getMapPins()
    }

}