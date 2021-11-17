package com.saber.flashlightappsmarket.ui.flashlights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlashlightsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Flashlights"
    }
    val text: LiveData<String> = _text
}