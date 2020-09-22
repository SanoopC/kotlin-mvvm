package com.exalture.atm

import android.content.Context
import android.content.SharedPreferences
import com.exalture.atm.Config.Companion.PREFERENCES_NAME

class SharedPreference(val context: Context?) {
    private val sharedPref: SharedPreferences =
        context!!.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveAccountNumber(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, value)
        editor.apply()
    }

    fun getAccountNumber(KEY_NAME: String): String {
        return sharedPref.getString(KEY_NAME, "").toString()
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}