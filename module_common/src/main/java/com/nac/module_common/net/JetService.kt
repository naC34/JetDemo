package com.nac.module_common.net

import retrofit2.http.GET

interface JetService {
    @GET("tree/json")
    suspend fun getData(): Any
}
