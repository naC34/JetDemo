package com.nac.module_common.net

import com.nac.lib_net.BaseUrl
import retrofit2.http.GET

@BaseUrl(url = "https://baidu.com/")
interface JetService {
    @GET("tree/json")
    suspend fun getData(): Any
}
