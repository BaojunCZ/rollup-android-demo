package demo.rollup.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import demo.rollup.MyApplication

class SPUtil<T>(private val key: String) {

    private val prefs: SharedPreferences by lazy {
        MyApplication.instance.getSharedPreferences("sp", Context.MODE_PRIVATE)
    }

    @SuppressLint("CommitPrefEdits")
    fun put(value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalArgumentException("SharedPreferences can't be save this type")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun get(default: T): T = with(prefs) {
        val res = when (default) {
            is Long -> getLong(key, default)
            is String -> getString(key, default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else -> throw IllegalArgumentException("SharedPreferences can't be get this type")
        }
        return res as T
    }
}