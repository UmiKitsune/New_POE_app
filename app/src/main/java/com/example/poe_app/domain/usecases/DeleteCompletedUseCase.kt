package com.example.poe_app.domain.usecases

import com.example.poe_app.data.database.entities.TitleEntity
import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.domain.AppRepository

class DeleteCompletedUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(title: TitleEntity) =
        repository.deleteCompleted(title)
}