package com.example.restaurantapp.uiScreen.logInOwnerScreen

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

//    fun isLoggedIn(): Boolean {
//        return sharedPreferences.getBoolean("isLoggedIn", false)
//    }

    fun setLoggedIn(value: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", value).apply()
    }

    fun validateCredentials(username: String, password: String): Boolean {
        val storedUsername = sharedPreferences.getString("username", null)
        val storedPassword = sharedPreferences.getString("password", null)
        return username == storedUsername && password == storedPassword
    }

    fun saveCredentials(username: String, password: String) {
        sharedPreferences.edit()
            .putString("username", username)
            .putString("password", password)
            .apply()
    }


    fun isNewUser(): Boolean {
        return sharedPreferences.getBoolean("isNewUser", true)
    }

    fun setNewUser(isNewUser: Boolean) {
        sharedPreferences.edit().putBoolean("isNewUser", isNewUser).apply()
    }
}
