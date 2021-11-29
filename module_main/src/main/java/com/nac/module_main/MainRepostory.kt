package com.nac.module_main

import com.nac.module_common.db.entity.Jet
import com.nac.module_common.db.dao.JetDao
import com.nac.module_common.net.JetService
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