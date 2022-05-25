package com.example.poe_app.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TitleWithMissionsEntity(
    @Embedded val title: TitleEntity,

    @Relation(parentColumn = "id", entityColumn = "titleId")
    val missions: List<MissionEntity>
)


