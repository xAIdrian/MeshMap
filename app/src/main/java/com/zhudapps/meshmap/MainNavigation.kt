package com.zhudapps.meshmap

import com.zhudapps.meshmap.model.MapPin

/**
 * Created by adrian mohnacs on 2019-06-28
 */
interface MainNavigation {

    fun getPinList(): List<MapPin>
}