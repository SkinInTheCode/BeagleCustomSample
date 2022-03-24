package com.example.designsystem.component.sharedpreference

interface Storage {

    fun retrieve(key: String): String?

    fun save(key: String, data: String)

    fun <T>retrieve(key: String, clazz: Class<T>): T?

    fun <T>save(key: String, data: T)
}