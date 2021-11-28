package com.nac.jetdemo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JetDao {

    @Query("select * from jet")
    fun getJetData(): Flow<List<Jet>>

    @Insert
    suspend fun addJetData(jet: Jet)
}