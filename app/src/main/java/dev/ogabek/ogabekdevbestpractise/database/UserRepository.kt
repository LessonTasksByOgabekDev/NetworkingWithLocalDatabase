package dev.ogabek.ogabekdevbestpractise.database

import android.app.Application
import dev.ogabek.ogabekdevbestpractise.helper.Logger
import dev.ogabek.ogabekdevbestpractise.manager.RoomManager

class UserRepository(application: Application) {

    private val TAG: String = UserRepository::class.java.simpleName

    private val db = RoomManager.getDatabase(application)
    private val userDao: UserDao = db!!.userDao()

    fun getUsers(): List<UserDB> {
        Logger.d(TAG, "${userDao.getUsers()}")
        return userDao.getUsers()
    }

    fun saveUser(userDB: UserDB) {
        Logger.d(TAG, "Saved")
        userDao.saveUser(userDB)
    }

    fun deleteUsers() {
        Logger.d(TAG, "Database cleared")
        userDao.deleteAllUsers()
    }

}