package com.example.clothingsuggester.util

import android.content.Context


class SharedPreferenceUtil(context: Context) {

     private val preferences = context.getSharedPreferences(
        Constants.SHARED_PREFS_NAME,
        Context.MODE_PRIVATE
    )
    fun checkContainData(key: String) =preferences.contains(key)

    fun saveClothesId(key: String, value: Int) = preferences.edit().putInt(key, value).apply()

    fun saveCurrentDate(key: String, value: String) = preferences.edit().putString(key, value).apply()

    fun getClothesId(key: String, defaultValue: Int) = preferences.getInt(key, defaultValue)

    fun getCurrentDate(key: String, defaultValue: String) = preferences . getString (key, defaultValue)



}
