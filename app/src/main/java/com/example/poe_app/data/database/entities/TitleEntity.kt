package com.example.poe_app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "title")
data class TitleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val titleId: Int,
    val titleName: String,
    val description: String,
    val hmCompleted: String,
    val isCompleted: Boolean
)