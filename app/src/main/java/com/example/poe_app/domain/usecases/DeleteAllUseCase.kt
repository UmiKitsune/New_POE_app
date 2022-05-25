package com.example.poe_app.domain.usecases

import com.example.poe_app.domain.AppRepository

class DeleteAllUseCase(private val repository: AppRepository) {
    suspend operator fun invoke() =
        repository.deleteAll()
}