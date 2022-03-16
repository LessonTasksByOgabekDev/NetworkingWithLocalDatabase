package dev.ogabek.ogabekdevbestpractise.activity

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dev.ogabek.ogabekdevbestpractise.R
import dev.ogabek.ogabekdevbestpractise.database.UserDB
import dev.ogabek.ogabekdevbestpractise.database.UserRepository
import dev.ogabek.ogabekdevbestpractise.helper.Logger
import dev.ogabek.ogabekdevbestpractise.model.User
import dev.ogabek.ogabekdevbestpractise.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    private lateinit var text: TextView

    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {

        userList = ArrayList()

        text = findViewById(R.id.text)

        if (isInternetAvailable()) {
            text.text = "Online"

            getUser()

        } else {
            text.text = "Offline"

            getUsersFromDatabase()

        }

    }

    private fun getUsersFromDatabase() {
        val repository = UserRepository(application)
        Logger.d("TAG", repository.getUsers().toString())
    }

    private fun getUser() {
        RetrofitHttp.userService.getAllUsers().enqueue(object: Callback<ArrayList<User>>{
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.body() != null) {
                    Logger.d(TAG, "onResponse: ${response.body().toString()}")

                    userList.clear()
                    userList.addAll(response.body()!!)

                    saveToDatabase(response.body()!!)

                } else {
                    Logger.e(TAG, "onResponse: null")
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Logger.e(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }

    private fun saveToDatabase(respond: ArrayList<User>) {

        val repository = UserRepository(application)

        repository.deleteUsers()

        for (i in respond) {
            val userDB = UserDB(i.id!!, i.full_name, i.username, i.is_online)
            repository.saveUser(userDB)
        }

    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }

}