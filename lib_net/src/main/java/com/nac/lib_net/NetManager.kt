package com.nac.lib_net

import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Method

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
        url?.let {
            changeBaseUrl(T::class.java, it)
        }
        return retrofit.create(T::class.java)
    }

    /**
     * 修改指定ApiService接口的baseUrl
     * @param IApiService Api接口Class
     * @param newBaseUrl 新的baseUrl
     */
    fun changeBaseUrl(IApiService: Class<*>, newBaseUrl: String) {
        val retrofitClass = retrofit.javaClass
        val serviceMethodCacheField = retrofitClass.getDeclaredField("serviceMethodCache")
        serviceMethodCacheField.isAccessible = true

        // 获取到api method的缓存数据
        val serviceMethodCache = serviceMethodCacheField.get(retrofit) as Map<*, *>
        // Api接口名称
        val apiServiceName = IApiService.name
        // 相同IApiService内的方法共用一个HttpUrl实例
        val newHttpUrl = newBaseUrl.toHttpUrl()

        serviceMethodCache.forEach {
            val method = it.key as Method
            // 声明此方法的接口名
            val declaringClassName = method.declaringClass.name

            // 只对声明在IApiService内的方法进行baseUrl修改
            if (declaringClassName == apiServiceName) {
                val serviceMethodClass = it.value?.javaClass
                val requestFactoryField =
                    serviceMethodClass?.superclass?.getDeclaredField("requestFactory")
                requestFactoryField?.isAccessible = true
                val requestFactory = requestFactoryField?.get(it.value)

                val requestFactoryClass = requestFactory?.javaClass
                val baseUrlField = requestFactoryClass?.getDeclaredField("baseUrl")
                baseUrlField?.isAccessible = true

                // 将requestFactory内的baseUrl修改为新的baseUrl
                baseUrlField?.set(requestFactory, newHttpUrl)
            }
        }
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class BaseUrl(val url: String)
