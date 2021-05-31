package de.carriot.myapplication.utils

import android.util.Base64
import android.util.Log
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AESEncyption {

    //////// *** constants *** ///
    private const val ENCRYPTION_ALGORYTHM = "AES"
    private const val SECRET_KEY_ALGORYTHM = "PBKDF2WithHmacSHA1"
    private const val AES_MODE_M_OR_GREATER = "AES/GCM/NoPadding"
    private const val AES_MODE_LESS_THAN_M = "AES/CBC/PKCS5PADDING"

    private const val iterationCount = 1000
    private const val keyLength = 256
    private const val ivLength = 16

    fun encrypt(input: String) :  String? {
        return try {
            val ivParameterSpec = getIvParams()
            val key: SecretKey = getSecretKey()

            val cipher = Cipher.getInstance(AES_MODE_LESS_THAN_M)
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec)
            return Base64.encodeToString(cipher.doFinal(input.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e("AESEncryption", "Error while encrypting: $e")
            null
        }
    }

    fun decrypt(input : String) : String? {
        try {
            val ivParameterSpec =  getIvParams()
            val key: SecretKey = getSecretKey()

            val cipher = Cipher.getInstance(AES_MODE_LESS_THAN_M)
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            return  String(cipher.doFinal(Base64.decode(input, Base64.DEFAULT)))
        } catch (e : Exception) {
            Log.e("AESEncryption", "Error while decrypting: $e")
        }
        return null
    }

    private fun getSecretKey() : SecretKey {
        val salt: String = getSalt()
        val factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORYTHM)
        val spec =  PBEKeySpec(getEncryptionPassword().toCharArray(), Base64.decode(salt, Base64.DEFAULT), iterationCount, keyLength)
        val tmp = factory.generateSecret(spec)
        val secretKey =  SecretKeySpec(tmp.encoded, ENCRYPTION_ALGORYTHM)
        return secretKey
    }

    private fun getEncryptionPassword() : String {
        val defaultPassword = "NotSecureStaticPassword"
        return defaultPassword.substring(16)
    }

    private fun getIvParams() : IvParameterSpec {
        val iv = "bVQzNFNhRkQ1Njc4UUFaWA==" // base64 decode => mT34SaFD5678QAZX
        return IvParameterSpec(Base64.decode(iv, Base64.DEFAULT))
    }

    private fun getSalt() : String{
        val salt = "QWlGNHNhMTJTQWZ2bGhpV3U=" // base64 decode => AiF4sa12SAfvlhiWu
        return salt
    }
}