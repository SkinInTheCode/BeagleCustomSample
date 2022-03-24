package com.example.designsystem.component.sharedpreference

import android.app.Application
import android.content.Context
import com.google.gson.Gson

class StorageImpl(
    application: Application,
    key: String,
    private val gson: Gson
) : Storage {

    private val sharedPref = application.getSharedPreferences(key, Context.MODE_PRIVATE)

    override fun retrieve(key: String): String? = with (sharedPref) {
        getString(key, null)
    }

    override fun <T> retrieve(key: String, clazz: Class<T>): T? {
        return sharedPref.getString(key, null)?.let {
            gson.fromJson(it, clazz)
        } ?: kotlin.run { null }
    }

    override fun save(key: String, data: String) : Unit = with (sharedPref.edit()) {
        putString(key, data)
        commit()
    }

    override fun <T> save(key: String, data: T) : Unit = with (sharedPref.edit()) {
        putString(key, gson.toJson(data))
        commit()
    }

}