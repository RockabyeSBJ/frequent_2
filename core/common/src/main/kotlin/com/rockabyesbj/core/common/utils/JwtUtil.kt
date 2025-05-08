package com.rockabyesbj.core.common.utils

//Do you decode exp here directly from msal_config.json? Or do it in UserSession? I don't know what's better.

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import java.util.Base64

object JwtUtil {

    private val json = Json {
        ignoreUnknownKeys = true // handle extra claims gracefully
    }

    @Serializable
    data class JwtClaims(
        @SerialName("sub") val subject: String? = null,
        @SerialName("exp") val expiresAt: Long? = null,
        @SerialName("email") val email: String? = null,
        @SerialName("userType") val userType: String? = null
    )

    fun decodeClaims(token: String): JwtClaims? {
        return try {
            val payload = token.split(".").getOrNull(1) ?: return null
            val decoded = String(Base64.getUrlDecoder().decode(payload))
            json.decodeFromString<JwtClaims>(decoded)
        } catch (e: Exception) {
            null
        }
    }

    fun getClaim(token: String, claim: String): String? {
        return try {
            val payload = token.split(".").getOrNull(1) ?: return null
            val decoded = String(Base64.getUrlDecoder().decode(payload))
            val jsonObject = json.parseToJsonElement(decoded) as? JsonObject
            jsonObject?.get(claim)?.toString()?.trim('"')
        } catch (e: Exception) {
            null
        }
    }

    fun isTokenExpired(token: String): Boolean {
        val claims = decodeClaims(token)
        val exp = claims?.expiresAt ?: return true
        val now = System.currentTimeMillis() / 1000
        return now >= exp
    }
}
