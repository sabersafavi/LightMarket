package com.saber.flashlightappsmarket.ui.colored_lights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColoredLightsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Colored Lights"
    }
    val text: LiveData<String> = _text
}