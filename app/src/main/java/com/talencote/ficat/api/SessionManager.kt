package com.talencote.ficat.api

import android.content.Context
import android.content.SharedPreferences
import com.talencote.ficat.R
import com.talencote.ficat.data.models.User

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "ID_KEY"
        const val USER_USERNAME = "USERNAME_KEY"
        const val USER_EMAIL = "EMAIL_KEY"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun saveUser(user: User) {
        val editor = prefs.edit()
        editor.putLong(USER_ID, user.id)
        editor.putString(USER_USERNAME, user.username)
        editor.putString(USER_EMAIL, user.email)
        editor.apply()
    }

    fun fetchUser() : User {
        return (User(
                id = prefs.getLong(USER_ID, 0),
                username = prefs.getString(USER_USERNAME, "null"),
                email = prefs.getString(USER_EMAIL, "null")
        ))
    }

    fun clearData() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.remove(USER_ID)
        editor.remove(USER_USERNAME)
        editor.remove(USER_EMAIL)
        editor.apply()
    }
}