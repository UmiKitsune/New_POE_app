package com.example.poe_app.domain.models

import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.data.url.models.Title

data class TitleWithMissions(
    val title: Title,
    val missions: List<Mission>
)