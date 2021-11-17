package com.saber.flashlightappsmarket.ui.sos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SOSAlertViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SOS Alert"
    }
    val text: LiveData<String> = _text
}