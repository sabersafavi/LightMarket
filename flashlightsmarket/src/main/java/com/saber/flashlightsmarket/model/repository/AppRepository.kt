package com.saber.flashlightsmarket.model.repository

import com.saber.flashlightsmarket.model.AppDatabase
import com.saber.flashlightsmarket.model.LightApp
import com.saber.flashlightsmarket.network.NetworkService
import com.saber.flashlightsmarket.utils.Prefs

class AppRepository(
    private val prefs: Prefs, private val networkService: NetworkService, private val appDatabase: AppDatabase
) {

    // Items per page for query
    private val ITEMS_PER_PAGE = 200
    // Either open, closed, or all to filter by state.
    private val REQUEST_STATE = "all"

//    suspend fun getFlashlightsAppsFromDB() = appDatabase.lightsDao().getFlashLights()
//    suspend fun getColoredLightsAppsFromDB() = appDatabase.lightsDao().getColoredLights()
//    suspend fun getSOSAlertAppsFromDB() = appDatabase.lightsDao().getSOSAlerts()

    suspend fun nukeTable() {
        appDatabase.lightsDao().apply {
            nukeTable()
        }
    }
    suspend fun saveFlashLights(items: List<LightApp>) {
        items.map { it.lightType = 1  }
        appDatabase.lightsDao().apply {
//            nukeTable()
//            insertAll(items)
        }
    }

    suspend fun saveColoredLights(items: List<LightApp>) {
        items.map { it.lightType = 2  }
        appDatabase.lightsDao().apply {
//            nukeTable()
//            insertAll(items)
        }
    }

    suspend fun saveSOSAlertApps(items: List<LightApp>) {
        items.map { it.lightType = 3  }
        appDatabase.lightsDao().apply {
//            nukeTable()
//            insertAll(items)
        }
    }

    suspend fun getSOSAlerts(): List<LightApp> {
//        val items = getFlashlightsAppsFromDB()
        val items = networkService.getSOSAlertsAsync().await()
//        saveSOSAlertApps(items)
        return items
    }
}