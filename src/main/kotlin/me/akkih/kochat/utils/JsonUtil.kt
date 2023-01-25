package me.akkih.kochat.utils

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object JsonUtil {

    fun getDataJSON(): String? {
        try {
            val client = OkHttpClient()

            val url = HttpUrl.Builder()
                .scheme("https")
                .host("raw.githubusercontent.com")
                .addPathSegment("Akkihiko")
                .addPathSegment("KoChat")
                .addPathSegment("main")
                .addPathSegment("resources")
                .addPathSegment("data.json")
                .build()

            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            client.newCall(request).execute().use {
                val status = it.code

                if (status == 200) return it.body?.string()
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }

        return null
    }

}