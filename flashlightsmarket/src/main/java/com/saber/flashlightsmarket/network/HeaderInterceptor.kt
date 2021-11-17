package com.saber.flashlightsmarket.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Intercept all apis calls via Retrofit
 */

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().apply {
            addHeader("Content-Type", "application/json")
            addHeader("JsonStub-User-Key", "f5e0861a-b53d-4b80-9c28-2233780c3d5d")
            addHeader("JsonStub-Project-Key", "72123b98-8a8b-4486-b24b-949722c0e254")
        }.build()

        return chain.proceed(request)
    }
}