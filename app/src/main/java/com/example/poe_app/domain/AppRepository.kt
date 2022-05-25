package com.example.poe_app.domain

import androidx.lifecycle.LiveData
import com.example.poe_app.data.database.entities.TitleEntity
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity
import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.data.url.models.Title

interface AppRepository {
    suspend fun sendUserName(userName: String, language:String)

    suspend fun insertTitles(titles: List<Title>)

    suspend fun insertMissions(missions: List<Mission>)

    fun getDataFromDatabase(): LiveData<List<TitleWithMissionsEntity>>

    suspend fun getTitles(): List<TitleEntity>

    suspend fun deleteCompleted(title: TitleEntity)

    suspend fun deleteAll()
}