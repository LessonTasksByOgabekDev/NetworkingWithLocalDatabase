package dev.ogabek.ogabekdevbestpractise.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDB (

    @PrimaryKey
    val id: String,

    val full_name: String,
    val username: String,
    val is_online: Boolean

)