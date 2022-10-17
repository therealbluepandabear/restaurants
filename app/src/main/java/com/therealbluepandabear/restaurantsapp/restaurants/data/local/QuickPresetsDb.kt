package com.therealbluepandabear.restaurantsapp.restaurants.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [QuickPreset::class],
    version = 5,
)
abstract class QuickPresetsDb : RoomDatabase() {

    abstract val dao: QuickPresetsDao

    companion object {
        @Volatile
        private var INSTANCE: QuickPresetsDb? = null

        fun getInstance(app: Application): QuickPresetsDb {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(app).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(app: Application) =
            Room.databaseBuilder(app, QuickPresetsDb::class.java, "quickpresets_db")
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                prePopulateDb(getInstance(app).dao)
                            }
                        }
                    }
                )
                .fallbackToDestructiveMigration()
                .build()

        private suspend fun prePopulateDb(dao: QuickPresetsDao) {
            dao.add(
                QuickPreset(5, 5)
            )
        }
    }
}
