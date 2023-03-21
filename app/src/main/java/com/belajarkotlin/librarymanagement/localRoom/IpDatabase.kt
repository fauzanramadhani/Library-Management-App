package com.belajarkotlin.librarymanagement.localRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [IpEntity::class], version = 1, exportSchema = false)
abstract class IpDatabase: RoomDatabase() {

    abstract fun ipDao(): IpDao

    companion object {
        @Volatile
        private var INSTANCE: IpDatabase? = null

        fun getDatabase(context: Context): IpDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IpDatabase::class.java,
                    "ip_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}