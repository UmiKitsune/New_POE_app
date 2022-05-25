package com.example.poe_app.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.poe_app.data.database.PoeDatabase
import com.example.poe_app.data.database.entities.TitleEntity
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity
import com.example.poe_app.data.mapper.Mapper
import com.example.poe_app.data.url.UrlData
import com.example.poe_app.data.url.models.Mission
import com.example.poe_app.data.url.models.Title
import com.example.poe_app.domain.AppRepository

class AppRepositoryImpl(context: Context): AppRepository {

    private val dao = PoeDatabase.getInstance(context).getDao()

    override suspend fun insertTitles(titles: List<Title>) =
        dao.insertTitles(titles.map{ Mapper.toTitleEntity(it)})

    override suspend fun insertMissions(missions: List<Mission>) =
        dao.insertMissions(missions.map{ Mapper.toMissionEntity(it)})

    override fun getDataFromDatabase(): LiveData<List<TitleWithMissionsEntity>> =
        dao.getTitleWithMissions()

    override suspend fun getTitles(): List<TitleEntity> =
        dao.getTitles()

    override suspend fun deleteCompleted(title: TitleEntity) {
        dao.delete(title)
    }

    override suspend fun deleteAll() {
        dao.deleteTitles()
        dao.deleteMissions()
    }

    override suspend fun sendUserName(userName: String, language: String) =
        UrlData.getData(userName, language)
}