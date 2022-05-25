package com.example.poe_app.domain.usecases

import com.example.poe_app.domain.AppRepository

class SendUserNameUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(userName: String, language: String) =
        repository.sendUserName(userName, language)
}