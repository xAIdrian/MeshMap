package com.zhudapps.meshmap.domain.api

import android.hardware.usb.UsbEndpoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by adrian mohnacs on 2019-06-30
 */
@Singleton
object ApiServiceGenerator {

    const val BASE_URL = "https://annetog.gotenna.com"

    val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val retrofit = builder.build()

    val httpClient = OkHttpClient.Builder()

    /**
     * Allows us to encapsulate the creation of any number of retrofit endpoint classes
     */
    fun <T> createService(endpointClass: Class<T>): T {
        return retrofit.create(endpointClass)
    }
}