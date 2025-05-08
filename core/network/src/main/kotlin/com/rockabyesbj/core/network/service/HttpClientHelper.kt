package com.rockabyesbj.core.network.service

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpClientHelper @Inject constructor(
    private val okHttpClient: OkHttpClient
) {

    fun get(url: String, headers: Map<String, String> = emptyMap()): Response {
        val request = Request.Builder()
            .url(url)
            .apply {
                headers.forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return okHttpClient.newCall(request).execute()
    }

    fun post(url: String, jsonBody: String, headers: Map<String, String> = emptyMap()): Response {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonBody.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .apply {
                headers.forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return okHttpClient.newCall(request).execute()
    }


    fun delete(url: String, headers: Map<String, String> = emptyMap()): Response {
        val request = Request.Builder()
            .url(url)
            .delete()
            .apply {
                headers.forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return okHttpClient.newCall(request).execute()
    }
}
