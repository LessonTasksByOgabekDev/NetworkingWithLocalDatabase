package dev.ogabek.ogabekdevbestpractise.networking.service

import dev.ogabek.ogabekdevbestpractise.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("users")
    fun getAllUsers(): Call<ArrayList<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<User>

}