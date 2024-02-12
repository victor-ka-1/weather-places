package com.kasko.victor.weather_places.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kasko.victor.weather_places.data.datasource.local.model.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(cityEntity: CityEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCity(cityEntity: CityEntity)

    @Query("DELETE FROM city_entity")
    suspend fun clear()

    @Query("SELECT * FROM city_entity")
    suspend fun getCitiesFlow(): List<CityEntity>
}