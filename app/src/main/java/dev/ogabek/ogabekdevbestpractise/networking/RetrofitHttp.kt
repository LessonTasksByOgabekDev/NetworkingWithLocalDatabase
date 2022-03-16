package dev.ogabek.ogabekdevbestpractise.networking

import dev.ogabek.ogabekdevbestpractise.networking.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {

    companion object {

        private val TAG: String = RetrofitHttp::class.java.simpleName

        const val IS_TESTER: Boolean = true

        private const val SERVER_DEVELOPMENT = "https://6231834605f5f4d40d7bfc48.mockapi.io/api/v1/"
        private const val SERVER_PRODUCTION = "https://6231834605f5f4d40d7bfc48.mockapi.io/api/v1/"

        private fun server(): String {
            return if (IS_TESTER) {
                SERVER_DEVELOPMENT
            } else {
                SERVER_PRODUCTION
            }
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server())
                .build()
        }

        val userService: UserService = getRetrofit().create(UserService::class.java)

    }

}