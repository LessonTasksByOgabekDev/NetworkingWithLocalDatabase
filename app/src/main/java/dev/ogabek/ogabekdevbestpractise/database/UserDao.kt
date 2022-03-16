package dev.ogabek.ogabekdevbestpractise.database

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userDB: UserDB)

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserDB>

    @Query("DELETE FROM users")
    fun deleteAllUsers()

}