package com.example.emerchantpay.common

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object SecureTokenStorageUtil {
    private const val ALIAS = "MyKeyAlias"
    private const val PREFS_FILENAME = "MyAppPreferences"
    private const val TOKEN_IV = "TokenIV"
    private const val ENCRYPTED_TOKEN = "EncryptedToken"

    private fun createKey(alias: String): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .build()
        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    private fun getSecretKey(alias: String): SecretKey {
        val keystore = KeyStore.getInstance("AndroidKeyStore")
        keystore.load(null)
        return keystore.getKey(alias, null) as SecretKey
    }

    private fun encrypt(secretKey: SecretKey, token: String): Pair<String, String> {
        val cipher = Cipher.getInstance("${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = Base64.encodeToString(cipher.iv, Base64.DEFAULT)
        val ciphertext = cipher.doFinal(token.toByteArray(Charsets.UTF_8))
        val encryptedToken = Base64.encodeToString(ciphertext, Base64.DEFAULT)
        return Pair(iv, encryptedToken)
    }

    private fun decrypt(secretKey: SecretKey, iv: String, encryptedToken: String): String {
        val cipher = Cipher.getInstance("${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(Base64.decode(iv, Base64.DEFAULT)))
        val ciphertext = Base64.decode(encryptedToken, Base64.DEFAULT)
        val decryptedTokenBytes = cipher.doFinal(ciphertext)
        return String(decryptedTokenBytes, Charsets.UTF_8)
    }

    fun storeToken(context: Context, token: String) {
        val secretKey = createKey(ALIAS)
        val (iv, encryptedToken) = encrypt(secretKey, token)
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE).edit().apply {
            putString(TOKEN_IV, iv)
            putString(ENCRYPTED_TOKEN, encryptedToken)
        }.apply()
    }

    fun retrieveToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val iv = prefs.getString(TOKEN_IV, null)
        val encryptedToken = prefs.getString(ENCRYPTED_TOKEN, null)
        return if (iv != null && encryptedToken != null) {
            val secretKey = getSecretKey(ALIAS)
            decrypt(secretKey, iv, encryptedToken)
        } else {
            null
        }
    }
}