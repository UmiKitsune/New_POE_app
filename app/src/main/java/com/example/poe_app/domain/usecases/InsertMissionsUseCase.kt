package com.example.poe_app.domain.usecases

import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.domain.AppRepository

class InsertMissionsUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(missions: List<Mission>) =
        repository.insertMissions(missions)
}