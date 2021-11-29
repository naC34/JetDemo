package com.nac.module_common.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jet")
data class Jet(
    @PrimaryKey
    val id: String,
    val data: String,
    @ColumnInfo(name = "create_at")
    val createAt: Long
)
