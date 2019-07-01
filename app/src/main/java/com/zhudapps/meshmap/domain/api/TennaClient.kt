package com.zhudapps.meshmap.domain.api

import com.zhudapps.meshmap.model.MapPin
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by adrian mohnacs on 2019-06-30
 */
interface TennaClient {
    @GET("development/scripts/get_map_pins.php")
    fun fetchMapPins(): Call<List<MapPin>> //todo change to retrofit wrapping
}