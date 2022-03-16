package dev.ogabek.ogabekdevbestpractise.model

data class User(
    val full_name: String,
    val username: String,
    val is_online: Boolean,
    val id: String? = null
)
