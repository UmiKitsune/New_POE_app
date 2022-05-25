package com.example.poe_app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mission")
data class MissionEntity (
    @PrimaryKey
    val missionId: Int,
    val missionText: String,
    val isClicked: Boolean,
    val titleId: Int
)