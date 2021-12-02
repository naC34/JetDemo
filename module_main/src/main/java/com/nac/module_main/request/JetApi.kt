package com.nac.module_main.request

import com.nac.lib_net.BaseUrl
import retrofit2.http.GET

@BaseUrl(url = "https://baidu.com/")
interface JetApi {
    @GET("tree/json")
    suspend fun getData(): Any
}
