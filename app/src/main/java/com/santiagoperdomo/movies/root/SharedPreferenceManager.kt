package com.santiagoperdomo.movies.root

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager {

    companion object{

        private fun getSharedPreference(): SharedPreferences{
            return MyApp.getContext()!!.getSharedPreferences(Const.APP_SETTINGS_FILE, Context.MODE_PRIVATE)
        }

        fun setSomeStringValue(key: String, value: String){
            val editor = getSharedPreference().edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getSomeStringValue(key: String): String{
            return getSharedPreference().getString(key, "")!!
        }
    }
}