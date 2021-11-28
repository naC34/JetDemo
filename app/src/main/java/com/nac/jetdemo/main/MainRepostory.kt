package com.nac.jetdemo.main

import com.nac.jetdemo.local.Jet
import com.nac.jetdemo.local.JetDao
import com.nac.jetdemo.net.JetService
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class MainRepository @Inject constructor(
    private val service: JetService,
    private val local: JetDao
) {
    suspend fun getData(): Any {
        return service.getData()
    }

    suspend fun saveLocal(data: String) = local.addJetData(
        Jet(
            id = Random.nextLong().toString(),
            data = data,
            createAt = System.currentTimeMillis()
        )
    )

    fun getLocalData() = local.getJetData()
}