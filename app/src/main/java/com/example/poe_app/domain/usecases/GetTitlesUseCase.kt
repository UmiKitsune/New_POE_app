package com.example.poe_app.domain.usecases

import com.example.poe_app.data.database.entities.TitleEntity
import com.example.poe_app.domain.AppRepository

class GetTitlesUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(): List<TitleEntity> =
        repository.getTitles()
}