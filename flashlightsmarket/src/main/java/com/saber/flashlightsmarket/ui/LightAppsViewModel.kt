package com.saber.flashlightsmarket.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saber.flashlightsmarket.model.LightApp
import com.saber.flashlightsmarket.model.Response
import com.saber.flashlightsmarket.model.repository.AppRepository
import com.saber.flashlightsmarket.utils.enums.LightType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LightsAppViewModel(private val appRepository: AppRepository) : ViewModel() {

    val items = MutableLiveData<Response<List<LightApp>>>()
    lateinit var lightType: LightType

    init {
    }

    fun getLightApps(appType: LightType) {
        lightType = appType
        items.postValue(Response.Loading(true))
        val handler = CoroutineExceptionHandler { _, throwable ->
            items.postValue(Response.Error(throwable))
        }
        viewModelScope.launch(handler) {
            try {
                items.postValue(Response.Success(if(appType == LightType.FlashLight) appRepository.getFlashlightsApps() else if(appType == LightType.ColoredLight) appRepository.getColoredLightsApps() else appRepository.getSOSAlerts()))
            } catch (exception: Exception) {
//                items.postValue(
//                    Response.Error(
//                        exception,
//                        appRepository.getSOSAlertAppsFromDB()
//                    )
//                )
            }
        }
    }
}