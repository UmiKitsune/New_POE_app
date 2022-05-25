package com.example.poe_app.data.mapper

import com.example.poe_app.data.database.entities.MissionEntity
import com.example.poe_app.data.database.entities.TitleEntity
import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.data.url.models.Title

object Mapper {
    fun toTitleEntity(title: Title): TitleEntity =
        TitleEntity(title.titleId, title.titleName, title.description, title.isCompleted)

    fun toMissionEntity(mission: Mission): MissionEntity =
        MissionEntity(mission.missionId, mission.missionText, mission.isClicked, mission.titleId)

}