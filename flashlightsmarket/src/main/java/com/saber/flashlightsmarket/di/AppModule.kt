package com.saber.flashlightsmarket.di

import androidx.room.Room
import com.saber.flashlightsmarket.model.AppDatabase
import com.saber.flashlightsmarket.model.repository.AppRepository
import com.saber.flashlightsmarket.network.HeaderInterceptor
import com.saber.flashlightsmarket.network.NetworkService
import com.saber.flashlightsmarket.utils.Constants
import com.saber.flashlightsmarket.utils.Prefs
import com.saber.flashlightsmarket.utils.SharedPreferencesHelper
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.saber.flashlightsmarket.ui.colored_lights.ColoredLightsViewModel
import com.saber.flashlightsmarket.ui.flashlights.FlashlightsViewModel
import com.saber.flashlightsmarket.ui.sos.SOSAlertViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single { SharedPreferencesHelper(get()) }
    single { Prefs(get()) }
    single { AppRepository(get(), get(), get()) }

    single { GsonBuilder().create() }
    single { GsonConverterFactory.create(get()) }
    single { CoroutineCallAdapterFactory() }
    single { HeaderInterceptor() }
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single {
        OkHttpClient().newBuilder().apply {
            addInterceptor(get<HeaderInterceptor>())
            addInterceptor(get<HttpLoggingInterceptor>())
            connectTimeout(0, TimeUnit.SECONDS)
            readTimeout(0, TimeUnit.SECONDS)
            writeTimeout(0, TimeUnit.SECONDS)
        }.build()
    }
    single {
        Retrofit.Builder().apply {
            client(get())
            baseUrl(Constants.BASE_URL)
            addConverterFactory(get<GsonConverterFactory>())
            addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
        }.build()
    }
    single { get<Retrofit>().create(NetworkService::class.java) }
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "github_db").build() }

//    viewModel { ColoredLightsViewModel(get()) }
//    viewModel { FlashlightsViewModel(get()) }
    viewModel { SOSAlertViewModel(get()) }
}