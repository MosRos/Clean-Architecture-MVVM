package com.morostami.archsample.data

import com.morostami.archsample.domain.model.Coin
import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule
import java.security.SecureRandom
import javax.inject.Singleton


@Singleton
object MyRealmConfigs {

    // Generate a key
    // IMPORTANT! This is a silly way to generate a key. It is also never stored.
    // For proper key handling please consult:
    // * https://developer.android.com/training/articles/keystore.html
    // * http://nelenkov.blogspot.dk/2012/05/storing-application-secrets-in-androids.html
    // Generate a key
    // IMPORTANT! This is a silly way to generate a key. It is also never stored.
    // For proper key handling please consult:
    // * https://developer.android.com/training/articles/keystore.html
    // * http://nelenkov.blogspot.dk/2012/05/storing-application-secrets-in-androids.html
    var key = ByteArray(64)

    private fun getGeneratedKey() : ByteArray{
         SecureRandom().nextBytes(key)

        return key
    }

    @RealmModule(classes = [Coin::class])
    class CryptoCurrencyModule


    private val cryptocurrencyConfig = RealmConfiguration.Builder()
        .name("cryptocurrency.realm")
        .schemaVersion(1)
//        .addModule(CryptoCurrencyModule())
        .build()

    private val userConfig = RealmConfiguration.Builder()
        .name("user.realm")
        .schemaVersion(1)
//        .encryptionKey(key)
        .build()



    fun getCryptoCurrencyConfig() : RealmConfiguration {
        return cryptocurrencyConfig
    }

    fun getUserConfig() : RealmConfiguration {
        return userConfig
    }
}