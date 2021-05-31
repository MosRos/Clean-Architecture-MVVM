/*
 * *
 *  * Created by Moslem Rostami on 5/31/21 8:04 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 5/31/21 8:04 PM
 *
 */

package com.morostami.archsample.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import de.carriot.myapplication.utils.AESEncyption
import kotlinx.coroutines.flow.*
import java.io.IOException


private const val PREFS_MAME = "DataStore_Preferences"
private val Context.dataStore by preferencesDataStore(PREFS_MAME)
class DataStoreHelper constructor(context: Context) {
    private var dataStore: DataStore<Preferences>? = null
    init {
        dataStore = context.dataStore
    }

    fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

    suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun putString(key: String, value: String) {
        val encryptedKey: String? = AESEncyption.encrypt(key)
        val encryptedValue: String? = AESEncyption.encrypt(value)
        if (encryptedKey.isNullOrEmpty() || encryptedValue.isNullOrEmpty()) return

        val prefKeyName = stringPreferencesKey(encryptedKey)
        dataStore?.setValue(prefKeyName, encryptedValue)
    }

    suspend fun getString(key: String, defaultValue: String) : Flow<String?> {
        val encryptedKey: String? = AESEncyption.encrypt(key)
        if (encryptedKey.isNullOrEmpty()) return flowOf(defaultValue)

        val prefKeyName = stringPreferencesKey(encryptedKey)
        return dataStore?.getValueFlow(prefKeyName, defaultValue)?.map { str ->
                AESEncyption.decrypt(str)
            } ?: flowOf(defaultValue)
    }
    suspend fun putBoolean(key: String, value: Boolean) {
        val encryptedKey: String? = AESEncyption.encrypt(key)
        if (encryptedKey.isNullOrEmpty()) return

        val prefKeyName = booleanPreferencesKey(encryptedKey)
        dataStore?.setValue(prefKeyName, value)
    }

    suspend fun getBoolean(key: String, defaultValue: Boolean) : Flow<Boolean?> {
        val encryptedKey: String? = AESEncyption.encrypt(key)
        if (encryptedKey.isNullOrEmpty()) return flowOf(defaultValue)

        val prefKeyName = booleanPreferencesKey(encryptedKey)
        return dataStore?.getValueFlow(prefKeyName, defaultValue) ?: flowOf(defaultValue)
    }

    suspend fun putInt(key: String, value: Int) {
        val encryptedKey: String? = AESEncyption.encrypt(key)
        if (encryptedKey.isNullOrEmpty()) return

        val prefKeyName = intPreferencesKey(encryptedKey)
        dataStore?.setValue(prefKeyName, value)
    }

    suspend fun getInt(key: String, defaultValue: Int) : Flow<Int?> {
        val encryptedKey: String? = AESEncyption.encrypt(key)
        if (encryptedKey.isNullOrEmpty()) return flowOf(defaultValue)

        val prefKeyName = intPreferencesKey(encryptedKey)
        return dataStore?.getValueFlow(prefKeyName, defaultValue) ?: flowOf(defaultValue)
    }
}