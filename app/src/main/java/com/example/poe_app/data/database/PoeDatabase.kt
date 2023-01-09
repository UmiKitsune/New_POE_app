package com.example.poe_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.poe_app.data.database.entities.MissionEntity
import com.example.poe_app.data.database.entities.TitleEntity

@Database(entities = [TitleEntity::class, MissionEntity::class], version = 4, exportSchema = false)
abstract class PoeDatabase: RoomDatabase() {
    companion object{
        private var db: PoeDatabase? = null
        private const val DB_NAME = "poeLast.db"
        private val LOCK = Any()

        fun getInstance(context: Context): PoeDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        PoeDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun getDao(): PoeDao
}