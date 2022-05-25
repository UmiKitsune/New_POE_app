package com.example.poe_app.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poe_app.data.database.entities.MissionEntity
import com.example.poe_app.data.database.entities.TitleEntity
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity

@Dao
interface PoeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitles(titles: List<TitleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMissions(missions: List<MissionEntity>)

    @Transaction
    @Query("SELECT * FROM title")
    //todo: может быть тут не получать LiveData, а просто List
    fun getTitleWithMissions(): LiveData<List<TitleWithMissionsEntity>>

    @Query("UPDATE mission SET isClicked = :isClicked WHERE missionId = :missionId")
    suspend fun updateMissionWithComplete(isClicked: Boolean, missionId: Int)

    @Delete
    suspend fun delete(title: TitleEntity)

    @Query("SELECT * FROM title")
    suspend fun getTitles(): List<TitleEntity>

    @Query("DELETE FROM title")
    suspend fun deleteTitles()

    @Query("DELETE FROM mission")
    suspend fun deleteMissions()
}