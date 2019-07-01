package com.zhudapps.meshmap.domain.repo

import android.util.Log
import com.zhudapps.meshmap.domain.api.TennaClient
import com.zhudapps.meshmap.domain.room.MapPinDao
import com.zhudapps.meshmap.model.MapPin
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by adrian mohnacs on 2019-06-30
 */
class MapPinsRepository @Inject constructor(
    val dao: MapPinDao,
    val retroClient: TennaClient
): IMapPinsRepository {

    override fun getMapPins(): Observable<List<MapPin>> {
        return Observable.concatArray(
            getPinsFromCache(),
            getPinsFromApi()
        )
    }

    override fun getPinsFromCache(): Observable<List<MapPin>> {
        return dao.getPins().toObservable().doOnNext { Log.e("MapPinsRepository", it.toString()) }
    }

    override fun getPinsFromApi(): Observable<List<MapPin>> {
        return retroClient.fetchMapPins().filter { it.isEmpty() }
            .doOnNext {
                storePinsInCache(it)
            }
    }

    override fun storePinsInCache(users: List<MapPin>) {
        Observable.fromCallable { dao.getPins() }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.e("MapPinsRepository", it.toString())
            }
    }

}