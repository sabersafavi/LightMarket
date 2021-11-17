package com.saber.flashlightsmarket.ui.sos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saber.flashlightsmarket.model.LightApp
import com.saber.flashlightsmarket.model.Response
import com.saber.flashlightsmarket.model.repository.AppRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SOSAlertViewModel(private val appRepository: AppRepository) : ViewModel() {

    val items = MutableLiveData<Response<List<LightApp>>>()

    init {
        getSOSAlerts()
    }

    fun getSOSAlerts() {
        items.postValue(Response.Loading(true))
        val handler = CoroutineExceptionHandler { _, throwable ->
            items.postValue(Response.Error(throwable))
        }
        viewModelScope.launch(handler) {
            try {
                items.postValue(Response.Success(appRepository.getSOSAlerts()))
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