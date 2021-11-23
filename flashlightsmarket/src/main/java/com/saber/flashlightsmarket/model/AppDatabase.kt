package com.saber.flashlightsmarket.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LightApp::class], version = 3, exportSchema = false)
@TypeConverters(AppDateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lightsDao(): LightsDao
}