package com.saber.flashlightsmarket.model

import androidx.room.*

@Entity(tableName = "lights")
data class LightApp(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var lightType: Int = 1,
    var packageName: String = "",
    var name: String = "",
    var iconUrl: String = "",
    var price: String = "",
    var ratingValue: Double = 0.0,
    var ratingCount: Long = 0,
    var downloads: String = "",
    @TypeConverters(AppDateTypeConverter::class)
    var publishDate:AppDate,
    var version: String = "",
    var category: String = "",
    var developerName: String = "",
    var developerEmail: String = "",
    var developerAddress: String = ""
){
    fun getPriceString() =  "price :\n$price"
    fun getRate() =  "rate :$ratingValue \nrate count: $ratingCount"
    fun getDeveloperString() =  "developer :$developerName"
}


@Dao
interface LightsDao {

//    @TypeConverters(AppDateTypeConverter::class)
//    @Query("SELECT * FROM lights WHERE lightType == 1")
//    suspend fun getFlashLights(): ArrayList<LightApp>
//
//    @Query("SELECT * FROM lights WHERE lightType == 2")
//    suspend fun getColoredLights(): List<LightApp>
//
//    @Query("SELECT * FROM lights WHERE lightType == 3")
//    suspend fun getSOSAlerts(): List<LightApp>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(lightApp: LightApp)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(lightApps: List<LightApp>)
//
//    @Query("DELETE FROM lights")
//    suspend fun nukeTable()
}

data class AppDate(
    val year: Int = 1987,
    val month: Int = 1,
    val day: Int = 1
)