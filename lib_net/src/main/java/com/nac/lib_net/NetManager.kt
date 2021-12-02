package com.nac.lib_net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetManager {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }).build())
            .build()
    }

    inline fun <reified T> create(): T {
        val url = T::class.java.getAnnotation(BaseUrl::class.java)?.url

        Log.e("NetHelper === ", url ?: "hhhhh")
        // TODO 反射更改对应 baseUrl

        return retrofit.create(T::class.java)
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class BaseUrl(val url: String)
