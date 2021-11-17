package com.saber.flashlightsmarket.network

import com.saber.flashlightsmarket.model.LightApp
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NetworkService {

    @GET("flashlights")
    fun getFlashlightsAsync(): Deferred<List<LightApp>>

    @GET("colorlights")
    fun getColoredLightsAsync(): Deferred<List<LightApp>>

    @GET("sosalerts")
    fun getSOSAlertsAsync(): Deferred<List<LightApp>>
}