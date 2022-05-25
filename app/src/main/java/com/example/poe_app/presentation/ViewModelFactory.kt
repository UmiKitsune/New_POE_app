package com.example.poe_app.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.poe_app.data.AppRepositoryImpl
import com.example.poe_app.domain.usecases.*

class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository = AppRepositoryImpl(context)
    private val sendUserName = SendUserNameUseCase(repository)
    private val insertTitles = InsertTitlesUseCase(repository)
    private val insertMissions = InsertMissionsUseCase(repository)
    private val getData = GetDataFromDatabaseUseCase(repository)
    private val delete = DeleteCompletedUseCase(repository)
    private val deleteAll = DeleteAllUseCase(repository)
    private val getTitles = GetTitlesUseCase(repository)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MainFragmentViewModel::class.java -> {
                MainFragmentViewModel(
                    sendUserName,
                    insertTitles,
                    insertMissions,
                    getData,
                    delete,
                    deleteAll,
                    getTitles
                )
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}