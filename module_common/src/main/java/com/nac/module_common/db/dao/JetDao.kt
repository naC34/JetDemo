package com.nac.module_common.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nac.module_common.db.entity.Jet
import kotlinx.coroutines.flow.Flow

@Dao
interface JetDao {

    @Query("select * from jet")
    fun getJetData(): Flow<List<Jet>>

    @Insert
    suspend fun addJetData(jet: Jet)
}