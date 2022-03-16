package dev.ogabek.ogabekdevbestpractise.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ogabek.ogabekdevbestpractise.database.UserDB
import dev.ogabek.ogabekdevbestpractise.database.UserDao

@Database(entities = [UserDB::class], version = 1)
abstract class RoomManager : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: RoomManager? = null

        fun getDatabase(context: Context): RoomManager? {
            if (INSTANCE == null) {
                synchronized(RoomManager::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomManager::class.java, "users.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    }

}