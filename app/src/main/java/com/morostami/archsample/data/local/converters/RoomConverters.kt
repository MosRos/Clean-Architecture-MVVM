/*
 * *
 *  * Created by Moslem Rostami on 7/26/20 10:35 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/26/20 10:35 AM
 *
 */

package com.morostami.archsample.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.morostami.archsample.data.local.entities.CoinMarketCapsEntity
import com.morostami.archsample.data.local.entities.CoinPriceEntity
import com.morostami.archsample.data.local.entities.CoinTotalVolumeEntity

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun fromStringDoubleMap(value: Map<String, Double>?) : String? {
        if (value != null) return null
        val gson = Gson()
        val type = object : TypeToken<Map<String, Double>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toStringDoubleMap(strValue: String?) : Map<String, Double>? {
        if (strValue != null) return null
        val gson = Gson()
        val type = object : TypeToken<Map<String, Double>>() {}.type
        return gson.fromJson(strValue as String, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromPriceList(priceEntities: MutableList<CoinPriceEntity>?) : String? {
        if (priceEntities != null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CoinPriceEntity>>() {}.type
        return gson.toJson(priceEntities, type)
    }

    @TypeConverter
    @JvmStatic
    fun toPriceList(pricesString: String?) : MutableList<CoinPriceEntity>? {
        if (pricesString != null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CoinPriceEntity>>() {}.type
        return gson.fromJson(pricesString as String, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromMarketCapsList(mCaps: MutableList<CoinMarketCapsEntity>?) : String? {
        if (mCaps != null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CoinMarketCapsEntity>>() {}.type
        return gson.toJson(mCaps, type)
    }

    @TypeConverter
    @JvmStatic
    fun toMarketCapsList(mCapsString: String?) : MutableList<CoinMarketCapsEntity>? {
        if (mCapsString != null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CoinMarketCapsEntity>>() {}.type
        return gson.fromJson(mCapsString as String, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromTotalVolumeList(volumeCoinEntities: MutableList<CoinTotalVolumeEntity>?) : String? {
        if (volumeCoinEntities != null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CoinTotalVolumeEntity>>() {}.type
        return gson.toJson(volumeCoinEntities, type)
    }

    @TypeConverter
    @JvmStatic
    fun toTotalVolumeList(volumesString: String?) : MutableList<CoinTotalVolumeEntity>? {
        if (volumesString != null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CoinTotalVolumeEntity>>() {}.type
        return gson.fromJson(volumesString as String, type)
    }
}