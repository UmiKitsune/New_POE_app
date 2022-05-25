package com.example.poe_app.domain.usecases

import com.example.poe_app.data.url.models.Title
import com.example.poe_app.domain.AppRepository

class InsertTitlesUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(titles: List<Title>) =
        repository.insertTitles(titles)
}