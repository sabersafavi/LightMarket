package com.saber.flashlightsmarket.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AppDateTypeConverter {

    @TypeConverter
    fun fromString(value: String): AppDate {
        val type = object : TypeToken<AppDate>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromAppDate(appDate: AppDate): String {
        return Gson().toJson(appDate)
    }

    @TypeConverter
    fun fromAppArray(value: String): ArrayList<LightApp> {
        val type = object : TypeToken<ArrayList<LightApp>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromAppArray(items: ArrayList<LightApp>): String {
        return Gson().toJson(items)
    }
}