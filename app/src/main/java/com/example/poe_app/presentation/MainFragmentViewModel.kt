package com.example.poe_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity
import com.example.poe_app.data.url.UrlData
import com.example.poe_app.domain.usecases.*
import com.example.poe_app.presentation.utils.MyStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel(
    private val sendUserNameUseCase: SendUserNameUseCase,
    private val insertTitles: InsertTitlesUseCase,
    private val insertMissions: InsertMissionsUseCase,
    private val getData: GetDataFromDatabaseUseCase,
    private val delete: DeleteCompletedUseCase,
    private val deleteAllUseCase: DeleteAllUseCase,
    private val getTitles: GetTitlesUseCase
) : ViewModel() {

    val dataFromDB: LiveData<List<TitleWithMissionsEntity>> = getData()

    private var _state = MutableLiveData<MyStates>()
    val state: LiveData<MyStates> = _state

    private var _countCompleted: MutableLiveData<Int> = MutableLiveData(0)
    val countCompleted: LiveData<Int> = _countCompleted


    fun sendUserName(userName: String, language: String) = viewModelScope.launch {
        sendUserNameUseCase(userName, language)
        withContext(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                insertTitles(UrlData.titles)
                insertMissions(UrlData.missions)
            }
            completedTitles()
        }
        _state.value = MyStates.Success
    }

    fun deleteCompleted() = viewModelScope.launch {
        _state.value = MyStates.Loading
        withContext(Dispatchers.IO) {
            for (title in getTitles()){
                if (title.isCompleted) {
                    delete(title)
                }
            }
        }
        _state.value = MyStates.Success
    }

    private fun deleteAll() = viewModelScope.launch {
        _state.value = MyStates.Loading
        withContext(Dispatchers.IO) {
            deleteAllUseCase()
        }
    }

    fun upLoad(userName: String, language: String) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            deleteAll()
        }
        withContext(Dispatchers.Default) {
            sendUserName(userName, language)
        }
    }

    private fun completedTitles() = viewModelScope.launch {
        var count = 0
        for (title in getTitles()){
            if (title.isCompleted) {
                count++
            }
        }
        _countCompleted.value = count
    }

}