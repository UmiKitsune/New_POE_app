package com.example.poe_app.domain.usecases

import androidx.lifecycle.LiveData
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity
import com.example.poe_app.domain.AppRepository

class GetDataFromDatabaseUseCase(private val repository: AppRepository) {
    operator fun invoke() : LiveData<List<TitleWithMissionsEntity>> =
        repository.getDataFromDatabase()
}