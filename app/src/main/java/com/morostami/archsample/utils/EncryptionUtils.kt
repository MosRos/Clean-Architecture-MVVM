package com.morostami.archsample.utils

import okhttp3.internal.and

object EncryptionUtils {

    private val hexArray: CharArray = "0123456789ABCDEF".toCharArray()

    fun byteToString(bytes: ByteArray) : String {

        val hexChars = CharArray(bytes.size * 2)
        for (j in hexChars.indices){
            val v: Int = bytes[j] and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }
}